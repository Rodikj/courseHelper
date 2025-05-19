// package mk.ukim.finki.coursehelper.service;

// import mk.ukim.finki.coursehelper.model.DocumentChunk;
// import mk.ukim.finki.coursehelper.model.Query;
// import mk.ukim.finki.coursehelper.model.RetrievalResult;
// import mk.ukim.finki.coursehelper.repository.RetrievalResultRepository;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;


// public interface RetrievalResultService {

//     RetrievalResult saveResult(RetrievalResult result);
//     Optional<RetrievalResult> getResultById(Long id);
//     List<RetrievalResult> getResultsByQuery(Query query);
//     List<RetrievalResult> getResultsByChunk(DocumentChunk chunk);
//     Optional<RetrievalResult> getResultByQueryAndChunk(Query query, DocumentChunk chunk);
//     void deleteResult(Long id);
//     List<RetrievalResult> getAllResults();
// }
