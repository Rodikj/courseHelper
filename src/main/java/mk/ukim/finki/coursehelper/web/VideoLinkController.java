package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.VideoLinkDTO;
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.model.VideoLink;
import mk.ukim.finki.coursehelper.service.CourseService;
import mk.ukim.finki.coursehelper.service.UserService;
import mk.ukim.finki.coursehelper.service.VideoLinkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/videos")
public class VideoLinkController {
    private final VideoLinkService videoLinkService;
    private final UserService userService;
    private final CourseService courseService;

    public VideoLinkController(VideoLinkService videoLinkService,
                               UserService userService,
                               CourseService courseService) {
        this.videoLinkService = videoLinkService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VideoLinkDTO create(@RequestParam("courseId") Long courseId,
                               @RequestBody VideoLinkDTO dto) {
        User user = userService.getUserById(dto.user().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        VideoLink v = new VideoLink();
        v.setUser(user);
        v.setCourse(course);
        v.setYt_id_link(dto.yt_id_link());
        v.setFile_name(dto.file_name());
        v.setFile_type(dto.file_type());
        v.setMd5(dto.file_name());
        v.setUpload_date(LocalDate.now());
        v.setPocessed(false);
        VideoLink saved = videoLinkService.saveVideoLink(v);
        return new VideoLinkDTO(
                saved.getId(),
                saved.getUser(),
                saved.getYt_id_link(),
                saved.getFile_name(),
                saved.getFile_type(),
                saved.getUpload_date(),
                saved.isPocessed()
        );
    }

    @GetMapping
    public List<VideoLinkDTO> listAll() {
        return videoLinkService.getAllVideoLinks().stream()
                .map(v -> new VideoLinkDTO(
                        v.getId(),
                        v.getUser(),
                        v.getYt_id_link(),
                        v.getFile_name(),
                        v.getFile_type(),
                        v.getUpload_date(),
                        v.isPocessed()
                ))
                .collect(Collectors.toList());
    }
}