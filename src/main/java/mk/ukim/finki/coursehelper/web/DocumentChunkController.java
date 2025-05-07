package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.DocumentChunkDTO;
import mk.ukim.finki.coursehelper.model.DocumentChunk;
import mk.ukim.finki.coursehelper.model.File;
import mk.ukim.finki.coursehelper.service.DocumentChunkService;
import mk.ukim.finki.coursehelper.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/chunks")
public class DocumentChunkController {
    private final DocumentChunkService chunkService;
    private final FileService fileService;

    public DocumentChunkController(DocumentChunkService chunkService, FileService fileService) {
        this.chunkService = chunkService;
        this.fileService = fileService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentChunkDTO create(@RequestBody DocumentChunkDTO dto) {
        File file = fileService.getFileById(dto.file().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));
        DocumentChunk chunk = new DocumentChunk();
        chunk.setFile(file);
        chunk.setChunk_text(dto.chunk_text());
        chunk.setChunk_index(dto.chunk_index());
        DocumentChunk saved = chunkService.saveDocumentChunk(chunk);
        return new DocumentChunkDTO(saved.getId(), saved.getFile(), saved.getChunk_text(), saved.getChunk_index());
    }

    @GetMapping("/{id}")
    public DocumentChunkDTO getOne(@PathVariable Long id) {
        DocumentChunk c = chunkService.getDocumentChunkById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chunk not found"));
        return new DocumentChunkDTO(c.getId(), c.getFile(), c.getChunk_text(), c.getChunk_index());
    }

    @GetMapping
    public List<DocumentChunkDTO> listAll() {
        return chunkService.getAllDocumentChunks().stream()
                .map(c -> new DocumentChunkDTO(c.getId(), c.getFile(), c.getChunk_text(), c.getChunk_index()))
                .collect(Collectors.toList());
    }
}