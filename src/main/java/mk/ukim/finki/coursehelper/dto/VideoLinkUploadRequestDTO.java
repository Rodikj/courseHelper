package mk.ukim.finki.coursehelper.dto;



public record VideoLinkUploadRequestDTO(
        Long userId,
        String ytId,
        String fileName,
        String fileType
) {}
