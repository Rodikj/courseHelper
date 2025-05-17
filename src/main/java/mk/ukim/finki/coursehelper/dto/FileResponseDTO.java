<<<<<<< HEAD
//package mk.ukim.finki.coursehelper.dto;
//
//import java.time.LocalDate;
//
//public record FileResponseDTO(
//        Long fileId,
//        Long userId,
//        Long courseId,
//        String fileName,
//        String fileType,
//        LocalDate uploadDate,
//        boolean processed
//) {}
=======
>>>>>>> cc5b632 (modified everything)
package mk.ukim.finki.coursehelper.dto;

import java.time.LocalDate;

public record FileResponseDTO(
<<<<<<< HEAD
        Long    id,
        Long    userId,
        Long    courseId,
        String  fileName,
        String  fileType,
=======
        Long fileId,
        Long userId,
        Long courseId,
        String fileName,
        String fileType,
>>>>>>> cc5b632 (modified everything)
        LocalDate uploadDate,
        boolean processed
) {}
