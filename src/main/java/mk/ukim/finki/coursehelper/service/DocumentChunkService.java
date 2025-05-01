package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.DocumentChunk;
import mk.ukim.finki.coursehelper.repository.DocumentChunkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentChunkService {

    private final DocumentChunkRepository documentChunkRepository;

    public DocumentChunkService(DocumentChunkRepository documentChunkRepository) {
        this.documentChunkRepository = documentChunkRepository;
    }

    public DocumentChunk saveDocumentChunk(DocumentChunk documentChunk) {
        return documentChunkRepository.save(documentChunk);
    }

    public Optional<DocumentChunk> getDocumentChunkById(Long id) {
        return documentChunkRepository.findById(id);
    }

    public List<DocumentChunk> getAllDocumentChunks() {
        return documentChunkRepository.findAll();
    }

}
