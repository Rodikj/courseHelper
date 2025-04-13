package mk.ukim.finki.coursehelper.repository;

import mk.ukim.finki.coursehelper.model.VideoLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoLinkRepository extends JpaRepository<VideoLink, Long>
{

}
