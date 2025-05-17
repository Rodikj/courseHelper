package mk.ukim.finki.coursehelper.dto;

import mk.ukim.finki.coursehelper.model.File;

public record DocumentChunkDTO(
        Long id,
//        File file,
        Long fileId,
        String chunk_text,
        int chunk_index
) {
}
