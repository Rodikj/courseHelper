package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CourseService
{

    Course saveCourse(Course course);
    Course createCourse(Long userId, String courseName);
    Optional<Course> getCourseById(Long id);
    List<Course> getAllCourses();
    void deleteCourse(Long id);
    List<Course> getCoursesForUser(Long userId);

}