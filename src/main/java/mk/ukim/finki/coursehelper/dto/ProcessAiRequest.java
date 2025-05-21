package mk.ukim.finki.coursehelper.dto;

import java.util.List;

public record ProcessAiRequest(
        ConversationHistory conversation_history,
        String contentUrl,
        String model_provider,
        String model_name,
        String api_key) {

    public record ConversationHistory(List<Message> messages) {
    }

    public record Message(String role, String content) {
    }
}
