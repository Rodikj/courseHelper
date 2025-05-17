package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.VideoLinkUploadRequestDTO;
import mk.ukim.finki.coursehelper.dto.VideoLinkResponseDTO;
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.model.VideoLink;
import mk.ukim.finki.coursehelper.service.CourseService;
import mk.ukim.finki.coursehelper.service.UserService;
import mk.ukim.finki.coursehelper.service.VideoLinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/courses/{courseId}/videos")
public class VideoController {
    private final VideoLinkService videoLinkService;
    private final UserService userService;
    private final CourseService courseService;

    public VideoController(VideoLinkService videoLinkService,
                                 UserService userService,
                                 CourseService courseService) {
        this.videoLinkService = videoLinkService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public VideoLinkResponseDTO addVideo(
            @PathVariable Long courseId,
            @RequestBody VideoLinkUploadRequestDTO dto
    ) {
        User user = userService.getUserById(dto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        VideoLink v = new VideoLink();
        v.setUser(user);
        v.setCourse(course);
        v.setYt_id_link(dto.ytId());
        v.setFile_name(dto.fileName());
        v.setFile_type(dto.fileType());
        v.setMd5(dto.fileName());
        v.setUpload_date(LocalDate.now());
        v.setProcessed(false);
        VideoLink saved = videoLinkService.saveVideoLink(v);

        return new VideoLinkResponseDTO(
                saved.getId(),
                user.getId(),
                courseId,
                saved.getYt_id_link(),
                saved.getFile_name(),
                saved.getFile_type(),
                saved.getUpload_date(),
                saved.isProcessed()
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VideoLinkResponseDTO> listVideos(@PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        return videoLinkService.getLinksByCourse(course).stream()
                .map(v -> new VideoLinkResponseDTO(
                        v.getId(),
                        v.getUser().getId(),
                        courseId,
                        v.getYt_id_link(),
                        v.getFile_name(),
                        v.getFile_type(),
                        v.getUpload_date(),
                        v.isProcessed()
                ))
                .collect(Collectors.toList());
    }
}
