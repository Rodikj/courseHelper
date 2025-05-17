package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;

    public CourseService(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    // In CourseService.java

    public Course createCourse(Long userId, String courseName)
    {
        User user = userService.getUserById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Course course = new Course();
        course.setUser(user);
        course.setCourse_name(courseName);
        return saveCourse(course);
    }


    public Optional<Course> getCourseById(Long id)
    {
        return courseRepository.findById(id);
    }

    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }



}
