package mk.ukim.finki.coursehelper.repository;


import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long>
{
    List<File> findByUser(User user);
    List<File> findByCourse(Course course);
}
