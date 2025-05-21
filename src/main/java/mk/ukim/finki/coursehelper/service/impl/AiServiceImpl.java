package mk.ukim.finki.coursehelper.service.impl;

import mk.ukim.finki.coursehelper.dto.ProcessAiRequest;
import mk.ukim.finki.coursehelper.dto.ProcessAiResponse;
import mk.ukim.finki.coursehelper.dto.TaskStatusResponse;
import mk.ukim.finki.coursehelper.model.ConversationHistory;
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.repository.CourseRepository;
import mk.ukim.finki.coursehelper.service.AiService;
import mk.ukim.finki.coursehelper.service.ConversationHistoryService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AiServiceImpl implements AiService {

    private final RestTemplate restTemplate;
    private final ConversationHistoryService conversationHistoryService;
    private final CourseRepository courseRepository;

    @Value("${external.ai.url}")
    private String externalAiUrl;

    @Value("${api.key}")
    private String apiKey;

    public AiServiceImpl(RestTemplate restTemplate, ConversationHistoryService conversationHistoryService,
            CourseRepository courseRepository) {
        this.restTemplate = restTemplate;
        this.conversationHistoryService = conversationHistoryService;
        this.courseRepository = courseRepository;
    }

    @Override
    public ProcessAiResponse processRequest(ProcessAiRequest request) {
        ProcessAiRequest.ConversationHistory conversationHistory = request.conversation_history();

        if (conversationHistory == null) {
            throw new IllegalArgumentException("Conversation history cannot be null");
        }

        ProcessAiRequest requestWithKey = new ProcessAiRequest(
                new ProcessAiRequest.ConversationHistory(conversationHistory.messages()),
                request.contentUrl(),
                request.model_provider(),
                request.model_name(),
                apiKey);

        return restTemplate.postForObject(
                externalAiUrl + "/api/process-ai",
                requestWithKey,
                ProcessAiResponse.class);
    }

    @Override
    public String processRequestReturnTaskId(ProcessAiRequest request) {
        ProcessAiResponse response = processRequest(request);

        if (response == null || response.getTask_id() == null) {
            throw new RuntimeException("Failed to get task ID from AI service response");
        }

        return response.getTask_id();
    }

    @Override
    public String processRequestReturnTaskIdAndSave(ProcessAiRequest request, Long courseId) {
        ProcessAiRequest.ConversationHistory conversationHistory = request.conversation_history();

        if (conversationHistory == null) {
            throw new IllegalArgumentException("Conversation history cannot be null");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        // Send request with API key
        ProcessAiRequest requestWithKey = new ProcessAiRequest(
                new ProcessAiRequest.ConversationHistory(conversationHistory.messages()),
                request.contentUrl(),
                request.model_provider(),
                request.model_name(),
                apiKey);

        ProcessAiResponse response = restTemplate.postForObject(
                externalAiUrl + "/api/process-ai",
                requestWithKey,
                ProcessAiResponse.class);

        if (response == null || response.getTask_id() == null) {
            throw new RuntimeException("Failed to get task ID from AI service response");
        }

        String taskId = response.getTask_id();

        // Save user message
        // for (var message : conversationHistory.messages()) {
        // ConversationHistory ch = new ConversationHistory();
        // ch.setRole(message.role());
        // ch.setContent(message.content());
        // ch.setCourse(course);
        // // ch.setVideo(message.video());
        // // ch.setToolCalls(message.tool_calls());
        // // ch.setToolResults(message.tool_results());
        // conversationHistoryService.save(ch);
        // }

        // Poll until AI processing is done
        TaskStatusResponse status;
        int retryCount = 0;
        do {
            try {
                Thread.sleep(2000); // delay between polls
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            status = getTaskStatus(taskId);
            retryCount++;
        } while (!"SUCCESS".equalsIgnoreCase(status.getStatus()) && retryCount < 10);

        if (!"SUCCESS".equalsIgnoreCase(status.getStatus())) {
            throw new RuntimeException("AI task did not complete successfully");
        }

        // Save all model-generated messages
        Object result = status.getResult().get("conversation_history");
        if (result instanceof List<?> responseList) {
            for (Object obj : responseList) {
                if (obj instanceof Map<?, ?> map) {
                    ConversationHistory reply = new ConversationHistory();
                    reply.setRole((String) map.get("role"));
                    reply.setContent((String) map.get("content"));
                    reply.setVideo((String) map.get("video"));
                    reply.setToolCalls((String) map.get("tool_calls"));
                    reply.setToolResults((String) map.get("tool_results"));
                    reply.setCourse(course);
                    conversationHistoryService.save(reply);
                }
            }
        }

        return taskId;
    }

    @Override
    public TaskStatusResponse getTaskStatus(String taskId) {
        return restTemplate.getForObject(
                externalAiUrl + "/api/task-status/" + taskId,
                TaskStatusResponse.class);
    }
}
