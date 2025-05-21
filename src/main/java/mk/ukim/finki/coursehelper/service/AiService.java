package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.dto.ProcessAiRequest;
import mk.ukim.finki.coursehelper.dto.ProcessAiResponse;
import mk.ukim.finki.coursehelper.dto.TaskStatusResponse;

public interface AiService {
    String processRequestReturnTaskId(ProcessAiRequest request);

    ProcessAiResponse processRequest(ProcessAiRequest request);

    TaskStatusResponse getTaskStatus(String taskId);

    String processRequestReturnTaskIdAndSave(ProcessAiRequest request, Long courseId);
}
