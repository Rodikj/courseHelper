package mk.ukim.finki.coursehelper.dto;

public record ConversationMessageDTO(
        String role,
        String content,
        String video,
        String tool_calls,
        String tool_results) {
}
