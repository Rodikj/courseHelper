package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.Embedding;
import mk.ukim.finki.coursehelper.model.DocumentChunk;
import mk.ukim.finki.coursehelper.repository.EmbeddingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmbeddingService {
    private final EmbeddingRepository embeddingRepository;

    public EmbeddingService(EmbeddingRepository embeddingRepository) {
        this.embeddingRepository = embeddingRepository;
    }

    public Embedding saveEmbedding(Embedding embedding) {
        return embeddingRepository.save(embedding);
    }

    public Optional<Embedding> getEmbeddingById(Long id) {
        return embeddingRepository.findById(id);
    }

    public List<Embedding> getEmbeddingsByChunk(DocumentChunk chunk) {
        return embeddingRepository.findByDocumentChunk(chunk);
    }

    public Optional<Embedding> getEmbeddingByChunkId(Long chunkId) {
        return embeddingRepository.findByDocumentChunkId(chunkId);
    }

    public void deleteEmbedding(Long id) {
        embeddingRepository.deleteById(id);
    }

    public List<Embedding> getAllEmbeddings() {
        return embeddingRepository.findAll();
    }
}