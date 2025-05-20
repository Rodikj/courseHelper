package mk.ukim.finki.coursehelper.dto;

import mk.ukim.finki.coursehelper.model.User;

import java.util.List;

public record CourseDTO(
        Long id,
        Long userId,
        String course_name,
//        List<Long> fileIds,
//        List<Long> videoLinkIds
        List<Long> sourceIds

) {
}
