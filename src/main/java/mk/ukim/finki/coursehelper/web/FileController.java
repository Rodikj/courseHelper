package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.FileDTO;
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.File;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.service.CourseService;
import mk.ukim.finki.coursehelper.service.FileService;
import mk.ukim.finki.coursehelper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;
    private final UserService userService;
    private final CourseService courseService;

    public FileController(FileService fileService,
                          UserService userService,
                          CourseService courseService) {
        this.fileService = fileService;
        this.userService = userService;
        this.courseService = courseService;
    }

    /** Create a new File for a given course.
     *  Pass the courseId as a query parameter, e.g. POST /api/files?courseId=42
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileDTO create(
            @RequestParam("courseId") Long courseId,
            @RequestBody FileDTO dto
    ) {
        // 1) look up the User
        User user = userService.getUserById(dto.user().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        // 2) look up the Course
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"));

        // 3) build a fresh File entity (ignore dto.id/upload_date/processed)
        File f = new File();
        f.setUser(user);
        f.setCourse(course);
        f.setFile_name(dto.file_name());
        f.setFile_type(dto.file_type());
        // if you have a real MD5 of the content, compute it; otherwise:
        f.setMd5(dto.file_name());
        f.setUpload_date(LocalDate.now());
        f.setProcessed(false);

        // 4) save and return a FileDTO
        File saved = fileService.saveFile(f);
        return new FileDTO(
                saved.getId(),
                saved.getUser(),
                saved.getFile_name(),
                saved.getFile_type(),
                saved.getUpload_date(),
                saved.isProcessed()
        );
    }

    /** List **all** files (no course filter) */
    @GetMapping
    public List<FileDTO> listAll() {
        return fileService.getAllFiles().stream()
                .map(f -> new FileDTO(
                        f.getId(),
                        f.getUser(),
                        f.getFile_name(),
                        f.getFile_type(),
                        f.getUpload_date(),
                        f.isProcessed()
                ))
                .collect(Collectors.toList());
    }

    /** Get one by its ID */
    @GetMapping("/{id}")
    public FileDTO getOne(@PathVariable Long id) {
        File f = fileService.getFileById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "File not found"));
        return new FileDTO(
                f.getId(),
                f.getUser(),
                f.getFile_name(),
                f.getFile_type(),
                f.getUpload_date(),
                f.isProcessed()
        );
    }
}
