package mk.ukim.finki.coursehelper.dto;

import mk.ukim.finki.coursehelper.model.User;

import java.time.LocalDate;

public record FileDTO(
        Long id,
        User user,
        String file_name,
        String file_type,
        LocalDate upload_date,
        boolean processed
) {
}
