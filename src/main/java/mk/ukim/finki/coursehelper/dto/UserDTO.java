package mk.ukim.finki.coursehelper.dto;

public record UserDTO(
        Long id,
        String name,
        String surname,
        String email
        //, String password
) {
}