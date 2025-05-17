package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.DocumentChunk;
import mk.ukim.finki.coursehelper.repository.DocumentChunkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DocumentChunkService {

    DocumentChunk saveDocumentChunk(DocumentChunk documentChunk);
    Optional<DocumentChunk> getDocumentChunkById(Long id);
    List<DocumentChunk> getAllDocumentChunks();

}
