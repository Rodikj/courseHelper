package mk.ukim.finki.coursehelper.dto;

import java.util.List;

import mk.ukim.finki.coursehelper.model.ConversationMessage;

public record ConversationHistoryDTO(
                List<AiMessage> messages) {
}