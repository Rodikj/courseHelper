package mk.ukim.finki.coursehelper.dto;

import mk.ukim.finki.coursehelper.model.File;

public record DocumentChunkDTO(
        Long id,
//        File file,
<<<<<<< HEAD
<<<<<<< HEAD
//        Long fileId,
        Long sourceId,
=======
        Long fileId,
>>>>>>> 17fd241 (changes with RetrievalResultController)
=======
//        Long fileId,
        Long sourceId,
>>>>>>> 1742606 (changes with FileController and some processing ai files)
        String chunk_text,
        int chunk_index
) {
}
