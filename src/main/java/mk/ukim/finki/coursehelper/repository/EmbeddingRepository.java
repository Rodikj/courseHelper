package mk.ukim.finki.coursehelper.repository;

import mk.ukim.finki.coursehelper.model.Embedding;
import mk.ukim.finki.coursehelper.model.DocumentChunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmbeddingRepository extends JpaRepository<Embedding, Long> {
    // The Embedding entity must define the field as 'private DocumentChunk documentChunk;'
    List<Embedding> findByDocumentChunk(DocumentChunk documentChunk);
    Optional<Embedding> findByDocumentChunkId(Long chunkId);
}