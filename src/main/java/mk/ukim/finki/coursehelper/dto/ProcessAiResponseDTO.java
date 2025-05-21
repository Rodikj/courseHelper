package mk.ukim.finki.coursehelper.dto;

import java.util.List;

public record ProcessAiResponseDTO(
        String taskId,
        List<ConversationMessageDTO> conversation_history) {
}