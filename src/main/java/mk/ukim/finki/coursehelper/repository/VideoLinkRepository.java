package mk.ukim.finki.coursehelper.repository;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.VideoLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoLinkRepository extends JpaRepository<VideoLink, Long>
{
    List<VideoLink> findByCourse(Course course);
}
