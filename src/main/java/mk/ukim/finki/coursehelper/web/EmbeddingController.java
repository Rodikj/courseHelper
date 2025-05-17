//package mk.ukim.finki.coursehelper.web;
//
//
//
//import mk.ukim.finki.coursehelper.dto.EmbeddingDTO;
//import mk.ukim.finki.coursehelper.model.DocumentChunk;
//import mk.ukim.finki.coursehelper.model.Embedding;
//import mk.ukim.finki.coursehelper.service.DocumentChunkService;
//import mk.ukim.finki.coursehelper.service.EmbeddingService;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.time.LocalDate;
//import java.util.stream.Collectors;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/api/embeddings")
//public class EmbeddingController {
//    private final EmbeddingService embeddingService;
//    private final DocumentChunkService chunkService;
//
//    public EmbeddingController(EmbeddingService embeddingService, DocumentChunkService chunkService) {
//        this.embeddingService = embeddingService;
//        this.chunkService = chunkService;
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public EmbeddingDTO create(@RequestBody EmbeddingDTO dto) {
//        DocumentChunk chunk = chunkService.getDocumentChunkById(dto.document_chunk().getId())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chunk not found"));
//        Embedding e = new Embedding();
//        e.setDocumentChunk(chunk);
//        e.setVector(dto.vector());
//        e.setModel(dto.model());
//        e.setEmbedding_date(dto.embedding_date());
//        Embedding saved = embeddingService.saveEmbedding(e);
//        return new EmbeddingDTO(
//                saved.getId(),
//                saved.getDocumentChunk(),
//                saved.getVector(),
//                saved.getModel(),
//                saved.getEmbedding_date()
//        );
//    }
//
//    @GetMapping
//    public List<EmbeddingDTO> listAll() {
//        return embeddingService.getAllEmbeddings().stream()
//                .map(e -> new EmbeddingDTO(
//                        e.getId(),
//                        e.getDocumentChunk(),
//                        e.getVector(),
//                        e.getModel(),
//                        e.getEmbedding_date()
//                ))
//                .collect(Collectors.toList());
//    }
//}
//
