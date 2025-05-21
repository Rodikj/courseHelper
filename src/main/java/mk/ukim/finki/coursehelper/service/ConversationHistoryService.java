package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.ConversationHistory;

import java.util.List;

public interface ConversationHistoryService {
    ConversationHistory save(ConversationHistory conversationHistory);

    List<ConversationHistory> getByCourseId(Long courseId);
}
