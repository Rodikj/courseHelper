package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.CourseDTO;
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /** Create a blank course for a given user */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO create(@RequestParam Long userId,
                            @RequestParam String courseName) {
        Course c = courseService.createCourse(userId, courseName);
        return toDto(c);
    }

    /** List all courses */
    @GetMapping
    public List<CourseDTO> listAll() {
        return courseService.getAllCourses().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /** Get one course by ID, including file & video IDs */
    @GetMapping("/{id}")
    public CourseDTO getOne(@PathVariable Long id) {
        Course c = courseService.getCourseById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"));
        return toDto(c);
    }

    private CourseDTO toDto(Course c) {
        var fileIds = c.getFiles().stream()
                .map(f -> f.getId())
                .collect(Collectors.toList());
        var videoIds = c.getVideos().stream()
                .map(v -> v.getId())
                .collect(Collectors.toList());
        return new CourseDTO(
                c.getId(),
                c.getUser(),
                c.getCourse_name(),
                fileIds,
                videoIds
        );
    }
}
