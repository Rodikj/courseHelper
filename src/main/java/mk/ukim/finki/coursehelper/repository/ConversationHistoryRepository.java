package mk.ukim.finki.coursehelper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.ukim.finki.coursehelper.model.ConversationHistory;

@Repository
public interface ConversationHistoryRepository extends JpaRepository<ConversationHistory, Long> {
    List<ConversationHistory> findAllByCourseId(Long courseId);
}
