package mk.ukim.finki.coursehelper.dto;

public class AiMessage {
    private String role;
    private String content;
    private Object video; // Can be null or a structured video object
    private Object tool_calls;
    private Object tool_results;
}
