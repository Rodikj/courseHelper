package mk.ukim.finki.coursehelper.dto;

import mk.ukim.finki.coursehelper.model.DocumentChunk;
import mk.ukim.finki.coursehelper.model.Embedding;
import java.time.LocalDate;

public record EmbeddingDTO(
        Long id,
        DocumentChunk document_chunk,
        String vector,
        String model,
        LocalDate embedding_date
) {}

