<<<<<<< HEAD
// package mk.ukim.finki.coursehelper.service.impl;

// import mk.ukim.finki.coursehelper.model.DocumentChunk;
// import mk.ukim.finki.coursehelper.model.Embedding;
// import mk.ukim.finki.coursehelper.repository.EmbeddingRepository;
// import mk.ukim.finki.coursehelper.service.EmbeddingService;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class EmbeddingServiceImpl implements EmbeddingService
// {
//     private final EmbeddingRepository embeddingRepository;

//     public EmbeddingServiceImpl(EmbeddingRepository embeddingRepository)
//     {
//         this.embeddingRepository = embeddingRepository;
//     }

//     @Override
//     public Embedding saveEmbedding(Embedding embedding) {
//         return embeddingRepository.save(embedding);
//     }

//     @Override
//     public Optional<Embedding> getEmbeddingById(Long id) {
//         return embeddingRepository.findById(id);
//     }

//     @Override
//     public List<Embedding> getEmbeddingsByChunk(DocumentChunk chunk) {
//         return embeddingRepository.findByDocumentChunk(chunk);
//     }

//     @Override
//     public Optional<Embedding> getEmbeddingByChunkId(Long chunkId) {
//         return embeddingRepository.findByDocumentChunkId(chunkId);
//     }

//     @Override
//     public void deleteEmbedding(Long id) {
//         embeddingRepository.deleteById(id);
//     }

//     @Override
//     public List<Embedding> getAllEmbeddings() {
//         return embeddingRepository.findAll();
//     }
// }
=======
package mk.ukim.finki.coursehelper.service.impl;

public class EmbeddingServiceImpl {
}
>>>>>>> 3fc453b (updated user)
