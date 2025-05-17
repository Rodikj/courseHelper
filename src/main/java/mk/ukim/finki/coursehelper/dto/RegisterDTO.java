package mk.ukim.finki.coursehelper.dto;

public record RegisterDTO(
        String name,
        String surname,
        String email,
        String password
) {
}
