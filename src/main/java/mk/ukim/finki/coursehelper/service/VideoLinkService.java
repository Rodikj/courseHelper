package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.VideoLink;
import mk.ukim.finki.coursehelper.repository.VideoLinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface VideoLinkService {

    VideoLink saveVideoLink(VideoLink videoLink);
    Optional<VideoLink> getVideoLinkById(Long id);
    List<VideoLink> getAllVideoLinks();
    List<VideoLink> getLinksByCourse(Course course);


<<<<<<< HEAD
    // NEW: fetch all links for a specific course
    public List<VideoLink> getLinksByCourse(Course course) {
        return videoLinkRepository.findByCourse(course);
    }
=======
>>>>>>> f93b757 (some reshuffling with service more like wp with impl)
}
