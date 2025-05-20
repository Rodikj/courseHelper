package mk.ukim.finki.coursehelper.dto;

public record VideoDTO(
        String type,
        String uri,
        String path,
        DurationDTO duration
) {}

