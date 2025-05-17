package mk.ukim.finki.coursehelper.service.impl;

<<<<<<< HEAD
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.VideoLink;
import mk.ukim.finki.coursehelper.repository.VideoLinkRepository;
import mk.ukim.finki.coursehelper.service.VideoLinkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoLinkServiceImpl implements VideoLinkService
{
    private final VideoLinkRepository videoLinkRepository;

    public VideoLinkServiceImpl(VideoLinkRepository videoLinkRepository) {
        this.videoLinkRepository = videoLinkRepository;
    }

    @Override
    public VideoLink saveVideoLink(VideoLink videoLink) {
        return videoLinkRepository.save(videoLink);
    }

    @Override
    public Optional<VideoLink> getVideoLinkById(Long id) {
        return videoLinkRepository.findById(id);
    }

    @Override
    public List<VideoLink> getAllVideoLinks() {
        return videoLinkRepository.findAll();
    }

    @Override
    public List<VideoLink> getLinksByCourse(Course course) {
        return videoLinkRepository.findByCourse(course);
    }
=======
public class VideoLinkServiceImpl {
>>>>>>> 3fc453b (updated user)
}
