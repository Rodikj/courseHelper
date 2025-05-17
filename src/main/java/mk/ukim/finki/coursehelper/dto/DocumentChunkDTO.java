package mk.ukim.finki.coursehelper.dto;

import mk.ukim.finki.coursehelper.model.File;

public record DocumentChunkDTO(
        Long id,
//        File file,
<<<<<<< HEAD
//        Long fileId,
        Long sourceId,
=======
        Long fileId,
>>>>>>> 17fd241 (changes with RetrievalResultController)
        String chunk_text,
        int chunk_index
) {
}
