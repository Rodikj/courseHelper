package mk.ukim.finki.coursehelper.dto;


import mk.ukim.finki.coursehelper.model.User;
import java.time.LocalDateTime;

public record QueryDTO(
        Long id,
//        User user,
        Long userId,
        String query_text,
        LocalDateTime timestamp
) {}
