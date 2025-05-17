package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.VideoLink;
import mk.ukim.finki.coursehelper.repository.VideoLinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoLinkService {

    private final VideoLinkRepository videoLinkRepository;

    public VideoLinkService(VideoLinkRepository videoLinkRepository) {
        this.videoLinkRepository = videoLinkRepository;
    }

    public VideoLink saveVideoLink(VideoLink videoLink) {
        return videoLinkRepository.save(videoLink);
    }

    public Optional<VideoLink> getVideoLinkById(Long id) {
        return videoLinkRepository.findById(id);
    }

    public List<VideoLink> getAllVideoLinks() {
        return videoLinkRepository.findAll();
    }

    // NEW: fetch all links for a specific course
    public List<VideoLink> getLinksByCourse(Course course) {
        return videoLinkRepository.findByCourse(course);
    }
}
