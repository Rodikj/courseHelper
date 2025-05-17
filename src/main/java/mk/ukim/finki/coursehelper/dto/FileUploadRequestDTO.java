// src/main/java/mk/ukim/finki/coursehelper/dto/FileUploadRequestDTO.java
package mk.ukim.finki.coursehelper.dto;

public record FileUploadRequestDTO(
        Long userId,
        Long courseId,
        String fileName,
        String fileType
) {}
