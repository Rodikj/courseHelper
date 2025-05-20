package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.CourseDTO;
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.File;
import mk.ukim.finki.coursehelper.model.Source;
import mk.ukim.finki.coursehelper.service.CourseService;
import mk.ukim.finki.coursehelper.service.SourceService;
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
    private final SourceService sourceService;

    public CourseController(CourseService courseService, SourceService sourceService) {
        this.courseService = courseService;
        this.sourceService = sourceService;
    }

    /** Create a blank course for a given user */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO create(@RequestParam Long userId,
                            @RequestParam String courseName) {
        Course c = courseService.createCourse(userId, courseName);
        return toDto(c);
    }

//    /** List all courses */
//    @GetMapping
//    public List<CourseDTO> listAll() {
//        return courseService.getAllCourses().stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }


    @GetMapping
    public List<CourseDTO> listByUser(@RequestParam Long userId) {
        return courseService.getCoursesForUser(userId).stream()
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        // ensure it exists (404 if not)
        courseService.getCourseById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found: " + id));
        courseService.deleteCourse(id);
    }


    @DeleteMapping("/{courseId}/sources/{sourceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSource(@PathVariable Long courseId, @PathVariable Long sourceId) {
        Course c = courseService.getCourseById(courseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found: " + courseId));
        // ensure source belongs to course
        Source s = sourceService.getSourceById(sourceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Source not found: " + sourceId));
        if (!s.getCourse().getId().equals(c.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Source " + sourceId + " does not belong to course " + courseId);
        }
        sourceService.deleteSource(sourceId);
    }




    @GetMapping("/{id}/sources")
    public List<Long> listSources(@PathVariable Long id) {
        Course c = courseService.getCourseById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found: " + id));
        return c.getSources().stream()
                .map(Source::getId)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/files")
    public List<Long> listFiles(@PathVariable Long id) {
        Course c = courseService.getCourseById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found: " + id));
        return c.getSources().stream()
                .filter(s -> s instanceof File)
                .map(Source::getId)
                .collect(Collectors.toList());
    }



    private CourseDTO toDto(Course c) {
        List<Long> srcIds = c.getSources().stream()
                .map(Source -> Source.getId())
                .collect(Collectors.toList());
        return new CourseDTO(
                c.getId(),
                c.getUser().getId(),
                c.getCourse_name(),
                srcIds
        );
    }
}