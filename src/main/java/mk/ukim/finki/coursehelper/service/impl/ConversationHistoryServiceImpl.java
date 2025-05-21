package mk.ukim.finki.coursehelper.service.impl;

import mk.ukim.finki.coursehelper.model.ConversationHistory;
import mk.ukim.finki.coursehelper.repository.ConversationHistoryRepository;
import mk.ukim.finki.coursehelper.service.ConversationHistoryService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class ConversationHistoryServiceImpl implements ConversationHistoryService {

    private final ConversationHistoryRepository conversationHistoryRepository;

    public ConversationHistoryServiceImpl(ConversationHistoryRepository conversationHistoryRepository) {
        this.conversationHistoryRepository = conversationHistoryRepository;
    }

    @Transactional
    @Override
    public ConversationHistory save(ConversationHistory conversationHistory) {
        return conversationHistoryRepository.save(conversationHistory);
    }

    @Override
    public List<ConversationHistory> getByCourseId(Long courseId) {
        return conversationHistoryRepository.findAllByCourseId(courseId);
    }
}
