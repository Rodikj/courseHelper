package mk.ukim.finki.coursehelper.dto;

import mk.ukim.finki.coursehelper.model.User;

public record CourseDTO(
        Long id,
        User user,
        String course_name,
        String select_type_of_material
) {
}
