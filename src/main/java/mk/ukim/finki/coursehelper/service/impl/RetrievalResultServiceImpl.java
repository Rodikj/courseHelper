<<<<<<< HEAD
// package mk.ukim.finki.coursehelper.service.impl;

// import mk.ukim.finki.coursehelper.model.DocumentChunk;
// import mk.ukim.finki.coursehelper.model.Query;
// import mk.ukim.finki.coursehelper.model.RetrievalResult;
// import mk.ukim.finki.coursehelper.repository.RetrievalResultRepository;
// import mk.ukim.finki.coursehelper.service.RetrievalResultService;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class RetrievalResultServiceImpl implements RetrievalResultService
// {
//     private final RetrievalResultRepository retrievalResultRepository;

//     public RetrievalResultServiceImpl(RetrievalResultRepository retrievalResultRepository) {
//         this.retrievalResultRepository = retrievalResultRepository;
//     }

//     @Override
//     public RetrievalResult saveResult(RetrievalResult result) {
//         return retrievalResultRepository.save(result);
//     }

//     @Override
//     public Optional<RetrievalResult> getResultById(Long id) {
//         return retrievalResultRepository.findById(id);
//     }

//     @Override
//     public List<RetrievalResult> getResultsByQuery(Query query) {
//         return retrievalResultRepository.findByQuery(query);
//     }

//     @Override
//     public List<RetrievalResult> getResultsByChunk(DocumentChunk chunk) {
//         return retrievalResultRepository.findByDocumentChunk(chunk);
//     }

//     @Override
//     public Optional<RetrievalResult> getResultByQueryAndChunk(Query query, DocumentChunk chunk) {
//         return retrievalResultRepository.findByQueryAndDocumentChunk(query, chunk);
//     }

//     @Override
//     public void deleteResult(Long id) {
//         retrievalResultRepository.deleteById(id);
//     }

//     @Override
//     public List<RetrievalResult> getAllResults() {
//         return retrievalResultRepository.findAll();
//     }
// }
=======
package mk.ukim.finki.coursehelper.service.impl;

public class RetrievalResultServiceImpl {
}
>>>>>>> 3fc453b (updated user)
