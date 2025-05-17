// package mk.ukim.finki.coursehelper.service;

// import mk.ukim.finki.coursehelper.model.Embedding;
// import mk.ukim.finki.coursehelper.model.DocumentChunk;
// import mk.ukim.finki.coursehelper.repository.EmbeddingRepository;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;


<<<<<<< HEAD
// public interface EmbeddingService
// {


//     Embedding saveEmbedding(Embedding embedding);
//     Optional<Embedding> getEmbeddingById(Long id);
//     List<Embedding> getEmbeddingsByChunk(DocumentChunk chunk);
//     Optional<Embedding> getEmbeddingByChunkId(Long chunkId);
//     void deleteEmbedding(Long id);
//     List<Embedding> getAllEmbeddings();



// }
=======
public interface EmbeddingService
{


    Embedding saveEmbedding(Embedding embedding);
    Optional<Embedding> getEmbeddingById(Long id);
    List<Embedding> getEmbeddingsByChunk(DocumentChunk chunk);
    Optional<Embedding> getEmbeddingByChunkId(Long chunkId);
    void deleteEmbedding(Long id);
    List<Embedding> getAllEmbeddings();



}
>>>>>>> f93b757 (some reshuffling with service more like wp with impl)
