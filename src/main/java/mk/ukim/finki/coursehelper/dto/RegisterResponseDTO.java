package mk.ukim.finki.coursehelper.dto;

public record RegisterResponseDTO(
        Long id,
        String name,
        String surname,
        String email
) {
}
