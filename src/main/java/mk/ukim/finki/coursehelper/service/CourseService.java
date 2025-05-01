package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
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
