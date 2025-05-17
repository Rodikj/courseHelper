<<<<<<< HEAD
// package mk.ukim.finki.coursehelper.service.impl;
// import mk.ukim.finki.coursehelper.model.DocumentChunk;
// import mk.ukim.finki.coursehelper.repository.DocumentChunkRepository;
// import mk.ukim.finki.coursehelper.service.DocumentChunkService;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class DocumentChunkServiceImpl implements DocumentChunkService
// {
//     private final DocumentChunkRepository documentChunkRepository;

//     public DocumentChunkServiceImpl(DocumentChunkRepository documentChunkRepository)
//     {
//         this.documentChunkRepository = documentChunkRepository;
//     }

//     @Override
//     public DocumentChunk saveDocumentChunk(DocumentChunk documentChunk) {
//         return documentChunkRepository.save(documentChunk);
//     }

//     @Override
//     public Optional<DocumentChunk> getDocumentChunkById(Long id) {
//         return documentChunkRepository.findById(id);
//     }

//     @Override
//     public List<DocumentChunk> getAllDocumentChunks() {
//         return documentChunkRepository.findAll();
//     }
// }
=======
package mk.ukim.finki.coursehelper.service.impl;
import mk.ukim.finki.coursehelper.model.DocumentChunk;
import mk.ukim.finki.coursehelper.repository.DocumentChunkRepository;
import mk.ukim.finki.coursehelper.service.DocumentChunkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentChunkServiceImpl implements DocumentChunkService
{
    private final DocumentChunkRepository documentChunkRepository;

    public DocumentChunkServiceImpl(DocumentChunkRepository documentChunkRepository)
    {
        this.documentChunkRepository = documentChunkRepository;
    }

    @Override
    public DocumentChunk saveDocumentChunk(DocumentChunk documentChunk) {
        return documentChunkRepository.save(documentChunk);
    }

    @Override
    public Optional<DocumentChunk> getDocumentChunkById(Long id) {
        return documentChunkRepository.findById(id);
    }

    @Override
    public List<DocumentChunk> getAllDocumentChunks() {
        return documentChunkRepository.findAll();
    }
}
>>>>>>> 3fc453b (updated user)
