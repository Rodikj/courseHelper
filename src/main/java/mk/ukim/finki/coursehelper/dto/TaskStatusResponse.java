package mk.ukim.finki.coursehelper.dto;

import java.util.List;
import java.util.Map;

public class TaskStatusResponse {
    private String task_id;
    private String status;
    private Map<String, Object> result;

    // Default constructor
    public TaskStatusResponse() {
    }

    // Constructor with task_id and status
    public TaskStatusResponse(String task_id, String status) {
        this.task_id = task_id;
        this.status = status;
    }

    // Constructor with all fields
    public TaskStatusResponse(String task_id, String status, Map<String, Object> result) {
        this.task_id = task_id;
        this.status = status;
        this.result = result;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
