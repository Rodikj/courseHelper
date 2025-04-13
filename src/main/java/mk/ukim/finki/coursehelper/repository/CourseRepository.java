package mk.ukim.finki.coursehelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.coursehelper.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>
{

}
