package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.File;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.service.CourseService;
import mk.ukim.finki.coursehelper.service.FileService;
import mk.ukim.finki.coursehelper.service.UserService;
import mk.ukim.finki.coursehelper.dto.FileResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/courses/{courseId}/files")
public class FileController {

    private final Logger log = LoggerFactory.getLogger(FileController.class);

    @Value("${file.storage.location}")
    private String storageLocation;

    private final FileService fileService;
    private final UserService userService;
    private final CourseService courseService;

    public FileController(FileService fileService,
                          UserService userService,
                          CourseService courseService) {
        this.fileService    = fileService;
        this.userService    = userService;
        this.courseService  = courseService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FileResponseDTO upload(
            @PathVariable Long courseId,
            @RequestParam("userId") Long userId,
            @RequestParam("file")   MultipartFile multipartFile
    ) throws IOException {
        // look up course & user
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        User user     = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        log.info("Uploading file '{}' for user={} course={}",
                multipartFile.getOriginalFilename(), userId, courseId);

        // delegate to service
        File saved = fileService.storeFile(user, course, multipartFile, storageLocation);

        // build response DTO
        return new FileResponseDTO(
                saved.getId(),
                saved.getUser().getId(),
                saved.getCourse().getId(),
                saved.getFileName(),
                saved.getFileType(),
                saved.getUploadDate(),
                saved.isProcessed()
        );
    }
}