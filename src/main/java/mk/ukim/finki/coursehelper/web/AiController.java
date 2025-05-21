package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.ProcessAiRequest;

import mk.ukim.finki.coursehelper.dto.ProcessAiResponse;
import mk.ukim.finki.coursehelper.dto.TaskStatusResponse;
import mk.ukim.finki.coursehelper.model.ConversationHistory;
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.repository.CourseRepository;
import mk.ukim.finki.coursehelper.service.AiService;
import mk.ukim.finki.coursehelper.service.ConversationHistoryService;

import java.io.Console;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/process-ai")
    public TaskStatusResponse processAi(@RequestBody ProcessAiRequest request, @RequestParam Long courseId) {
        try {
            String taskId = aiService.processRequestReturnTaskIdAndSave(request, courseId);
            return returnTask(taskId);
        } catch (Exception e) {
            e.printStackTrace(); // log full stack trace
            throw new RuntimeException("Failed to process AI request: " + e.getMessage());
        }
    }

    @GetMapping("/task-status/{taskId}")
    public TaskStatusResponse getTaskStatus(@PathVariable String taskId) {
        return aiService.getTaskStatus(taskId);
    }

    @GetMapping("/task-status/{taskId}/wait")
    public TaskStatusResponse returnTask(@PathVariable String taskId) {
        for (int i = 0; i < 5; i++) {
            TaskStatusResponse response = aiService.getTaskStatus(taskId);

            if ("SUCCESS".equalsIgnoreCase(response.getStatus()) ||
                    "FAILURE".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

            try {
                Thread.sleep(3000); // wait for 3 seconds before next check
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted while waiting for task status", e);
            }
        }

        // Final fetch before timeout fallback
        return aiService.getTaskStatus(taskId);
    }
}
