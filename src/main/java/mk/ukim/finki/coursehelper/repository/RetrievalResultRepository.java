package mk.ukim.finki.coursehelper.repository;

import mk.ukim.finki.coursehelper.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RetrievalResultRepository extends JpaRepository<RetrievalResult, Long> {
    List<RetrievalResult> findByQuery(Query query);
    List<RetrievalResult> findByDocumentChunk(DocumentChunk documentChunk);
    Optional<RetrievalResult> findByQueryAndDocumentChunk(Query query, DocumentChunk documentChunk);

}
