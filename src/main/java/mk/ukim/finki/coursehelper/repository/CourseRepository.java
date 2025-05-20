package mk.ukim.finki.coursehelper.repository;

import mk.ukim.finki.coursehelper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.coursehelper.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>
{
    List<Course> findByUser(User user);
}