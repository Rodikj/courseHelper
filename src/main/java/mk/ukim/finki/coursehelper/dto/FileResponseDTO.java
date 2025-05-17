package mk.ukim.finki.coursehelper.dto;

import java.time.LocalDate;

public record FileResponseDTO(
        Long fileId,
        Long userId,
        Long courseId,
        String fileName,
        String fileType,
        LocalDate uploadDate,
        boolean processed
) {}
