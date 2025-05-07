package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.CourseDTO;
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.service.CourseService;
import mk.ukim.finki.coursehelper.service.UserService;
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
    private final UserService userService;

    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO create(@RequestBody CourseDTO dto) {
        // Extract user from DTO
        User user = userService.getUserById(dto.user().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Course course = new Course();
        course.setUser(user);
        course.setCourse_name(dto.course_name());
        course.setSelect_type_of_material(dto.select_type_of_material());
        Course saved = courseService.saveCourse(course);
        return new CourseDTO(saved.getId(), saved.getUser(), saved.getCourse_name(), saved.getSelect_type_of_material());
    }

    @GetMapping("/{id}")
    public CourseDTO getOne(@PathVariable Long id) {
        Course c = courseService.getCourseById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        return new CourseDTO(c.getId(), c.getUser(), c.getCourse_name(), c.getSelect_type_of_material());
    }

    @GetMapping
    public List<CourseDTO> listAll() {
        return courseService.getAllCourses().stream()
                .map(c -> new CourseDTO(c.getId(), c.getUser(), c.getCourse_name(), c.getSelect_type_of_material()))
                .collect(Collectors.toList());
    }
}