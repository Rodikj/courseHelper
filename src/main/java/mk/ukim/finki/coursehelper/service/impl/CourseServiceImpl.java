package mk.ukim.finki.coursehelper.service.impl;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.CourseRepository;
import mk.ukim.finki.coursehelper.service.CourseService;
import mk.ukim.finki.coursehelper.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService
{

        private final CourseRepository courseRepository;
    private final UserService userService;

    public CourseServiceImpl(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course createCourse(Long userId, String courseName)
    {
        User user = userService.getUserById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Course course = new Course();
        course.setUser(user);
        course.setCourse_name(courseName);
        return saveCourse(course);
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
