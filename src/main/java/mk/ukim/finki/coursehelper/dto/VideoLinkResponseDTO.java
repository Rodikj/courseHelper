package mk.ukim.finki.coursehelper.dto;



import java.time.LocalDate;

public record VideoLinkResponseDTO(
        Long id,
        Long userId,
        Long courseId,
        String ytId,
        String fileName,
        String fileType,
        LocalDate uploadDate,
        boolean processed
) {}
