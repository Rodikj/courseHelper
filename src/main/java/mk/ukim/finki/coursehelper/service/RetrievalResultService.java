package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.DocumentChunk;
import mk.ukim.finki.coursehelper.model.Query;
import mk.ukim.finki.coursehelper.model.RetrievalResult;
import mk.ukim.finki.coursehelper.repository.RetrievalResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetrievalResultService {
    private final RetrievalResultRepository retrievalResultRepository;

    public RetrievalResultService(RetrievalResultRepository retrievalResultRepository) {
        this.retrievalResultRepository = retrievalResultRepository;
    }

    public RetrievalResult saveResult(RetrievalResult result) {
        return retrievalResultRepository.save(result);
    }

    public Optional<RetrievalResult> getResultById(Long id) {
        return retrievalResultRepository.findById(id);
    }

    public List<RetrievalResult> getResultsByQuery(Query query) {
        return retrievalResultRepository.findByQuery(query);
    }

    public List<RetrievalResult> getResultsByChunk(DocumentChunk chunk) {
        return retrievalResultRepository.findByDocumentChunk(chunk);
    }

    public Optional<RetrievalResult> getResultByQueryAndChunk(Query query, DocumentChunk chunk) {
        return retrievalResultRepository.findByQueryAndDocumentChunk(query, chunk);
    }

    public void deleteResult(Long id) {
        retrievalResultRepository.deleteById(id);
    }

    public List<RetrievalResult> getAllResults() {
        return retrievalResultRepository.findAll();
    }
}
