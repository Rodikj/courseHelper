package mk.ukim.finki.coursehelper.web;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import mk.ukim.finki.coursehelper.dto.FileResponseDTO;
import mk.ukim.finki.coursehelper.dto.FileUploadRequestDTO;
>>>>>>> cc5b632 (modified everything)
=======
>>>>>>> 1742606 (changes with FileController and some processing ai files)
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.File;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.service.CourseService;
import mk.ukim.finki.coursehelper.service.FileService;
import mk.ukim.finki.coursehelper.service.UserService;
<<<<<<< HEAD
<<<<<<< HEAD
import mk.ukim.finki.coursehelper.dto.FileResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
=======
=======
import mk.ukim.finki.coursehelper.dto.FileResponseDTO;
>>>>>>> 1742606 (changes with FileController and some processing ai files)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
>>>>>>> cc5b632 (modified everything)
=======
>>>>>>> 1742606 (changes with FileController and some processing ai files)
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

<<<<<<< HEAD
<<<<<<< HEAD
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/courses/{courseId}/files")
public class FileController {

    private final Logger log = LoggerFactory.getLogger(FileController.class);

    @Value("${file.storage.location}")
    private String storageLocation;

=======
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
=======
import java.io.IOException;
>>>>>>> 1742606 (changes with FileController and some processing ai files)

@CrossOrigin
@RestController
@RequestMapping("/api/courses/{courseId}/files")
public class FileController {

    private final Logger log = LoggerFactory.getLogger(FileController.class);
<<<<<<< HEAD
>>>>>>> cc5b632 (modified everything)
=======

    @Value("${file.storage.location}")
    private String storageLocation;

>>>>>>> 1742606 (changes with FileController and some processing ai files)
    private final FileService fileService;
    private final UserService userService;
    private final CourseService courseService;

    public FileController(FileService fileService,
                          UserService userService,
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 1742606 (changes with FileController and some processing ai files)
                          CourseService courseService) {
        this.fileService    = fileService;
        this.userService    = userService;
        this.courseService  = courseService;
<<<<<<< HEAD
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
=======
                          CourseService courseService,
                          PythonService pythonService) {
        this.fileService = fileService;
        this.userService = userService;
        this.courseService = courseService;
        this.pythonService = pythonService;
=======
>>>>>>> 1742606 (changes with FileController and some processing ai files)
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
<<<<<<< HEAD

    /**
     * List all files under this course.
     */
    @GetMapping(produces = "application/json")
    public List<FileResponseDTO> listByCourse(@PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        return fileService.getFilesByCourse(course)
                .stream()
                .map(f -> new FileResponseDTO(
                        f.getId(),
                        f.getUser().getId(),
//                        course.getId(),
                        courseId,
                        f.getFile_name(),
                        f.getFile_type(),
                        f.getUpload_date(),
                        f.isProcessed()
                ))
                .collect(Collectors.toList());
    }
>>>>>>> cc5b632 (modified everything)
=======
>>>>>>> 1742606 (changes with FileController and some processing ai files)
}




<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 1742606 (changes with FileController and some processing ai files)


//package mk.ukim.finki.coursehelper.web;
//
//import mk.ukim.finki.coursehelper.dto.FileResponseDTO;
//import mk.ukim.finki.coursehelper.dto.FileUploadRequestDTO;
//import mk.ukim.finki.coursehelper.model.Course;
//import mk.ukim.finki.coursehelper.model.File;
//import mk.ukim.finki.coursehelper.model.User;
//import mk.ukim.finki.coursehelper.service.CourseService;
//import mk.ukim.finki.coursehelper.service.FileService;
//import mk.ukim.finki.coursehelper.service.PythonService;
//import mk.ukim.finki.coursehelper.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/courses/{courseId}/files")
//@CrossOrigin
//public class FileController {
//
//    @Value("${file.storage.location}")
//    private String storageLocation;
//
//    private final FileService fileService;
//    private final UserService userService;
//    private final CourseService courseService;
//
//    public FileController(FileService fileService,
//                          UserService userService,
//                          CourseService courseService) {
//        this.fileService = fileService;
//        this.userService = userService;
//        this.courseService = courseService;
//    }
//
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
<<<<<<< HEAD
//    @ResponseStatus(HttpStatus.CREATED)
//    public FileResponseDTO upload(
//            @PathVariable Long courseId,
//            @RequestParam("userId") Long userId,
//            @RequestParam("file") MultipartFile multipartFile
//    ) throws IOException {
//        // 1) look up user & course
=======
//    @PostMapping(
//            path = "/json-upload",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
=======
>>>>>>> 1742606 (changes with FileController and some processing ai files)
//    @ResponseStatus(HttpStatus.CREATED)
//    public FileResponseDTO upload(
//            @PathVariable Long courseId,
//            @RequestParam("userId") Long userId,
//            @RequestParam("file") MultipartFile multipartFile
//    ) throws IOException {
//        // 1) look up user & course
//        User user = userService.getUserById(userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//        Course course = courseService.getCourseById(courseId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
//
//        // 2) delegate to service for storing + persisting
//        File saved = fileService.storeFile(user, course, multipartFile, storageLocation);
//
//        // 3) map to DTO
//        return new FileResponseDTO(
//                saved.getId(),
//                user.getId(),
//                course.getId(),
//                saved.getFileName(),
//                saved.getFileType(),
//                saved.getUploadDate(),
//                saved.isProcessed()
//        );
//    }
//}
//
<<<<<<< HEAD
//        String response = pythonService.uploadFile(file);
//        return ResponseEntity.ok(response);
//    }




//    @PostMapping(consumes = "application/octet-stream")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Map<String, Object> uploadFileRaw(
//            @RequestParam("courseId") Long courseId,
//            @RequestParam("userId") Long userId,
//            @RequestHeader("File-Name") String filename,
//            @RequestBody byte[] fileContent
//    ) throws Exception {
>>>>>>> cc5b632 (modified everything)
//        User user = userService.getUserById(userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//        Course course = courseService.getCourseById(courseId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
//
<<<<<<< HEAD
//        // 2) delegate to service for storing + persisting
//        File saved = fileService.storeFile(user, course, multipartFile, storageLocation);
//
//        // 3) map to DTO
//        return new FileResponseDTO(
//                saved.getId(),
//                user.getId(),
//                course.getId(),
//                saved.getFileName(),
//                saved.getFileType(),
//                saved.getUploadDate(),
//                saved.isProcessed()
//        );
//    }
//}
//
//
//
////@CrossOrigin
////@RestController
//////@RequestMapping("/api/files")
////@RequestMapping("/api/courses/{courseId}/files")
////public class FileController {
////
////
////    private final Logger log = LoggerFactory.getLogger(FileController.class);
////    private final FileService fileService;
////    private final UserService userService;
////    private final CourseService courseService;
////    private final PythonService pythonService;
////
////    public FileController(FileService fileService,
////                          UserService userService,
////                          CourseService courseService,
////                          PythonService pythonService) {
////        this.fileService = fileService;
////        this.userService = userService;
////        this.courseService = courseService;
////        this.pythonService = pythonService;
////    }
////
////
////
////    @PostMapping(consumes = "application/json", produces = "application/json")
////    @ResponseStatus(HttpStatus.CREATED)
////    public FileResponseDTO createJson(
////            @PathVariable Long courseId,
////            @RequestBody FileUploadRequestDTO dto
////    ) {
////        // 1) load course
////        Course course = courseService.getCourseById(courseId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
////        // 2) load user
////        User user = userService.getUserById(dto.userId())
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
////        // 3) build & save File
////        File f = new File();
////        f.setCourse(course);
////        f.setUser(user);
////        f.setFile_name(dto.fileName());
////        f.setFile_type(dto.fileType());
////        f.setMd5("TODO‑compute‑md5");
////        f.setUpload_date(LocalDate.now());
////        f.setProcessed(false);
////        File saved = fileService.saveFile(f);
////
////        // 4) return response DTO
////        return new FileResponseDTO(
////                saved.getId(),
////                user.getId(),
////                course.getId(),
////                saved.getFile_name(),
////                saved.getFile_type(),
////                saved.getUpload_date(),
////                saved.isProcessed()
////        );
////    }
////
////    /**
////     * List all files under this course.
////     */
////    @GetMapping(produces = "application/json")
////    public List<FileResponseDTO> listByCourse(@PathVariable Long courseId) {
////        Course course = courseService.getCourseById(courseId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
////        return fileService.getFilesByCourse(course)
////                .stream()
////                .map(f -> new FileResponseDTO(
////                        f.getId(),
////                        f.getUser().getId(),
//////                        course.getId(),
////                        courseId,
////                        f.getFile_name(),
////                        f.getFile_type(),
////                        f.getUpload_date(),
////                        f.isProcessed()
////                ))
////                .collect(Collectors.toList());
////    }
////}
//
//
//
//
////    @PostMapping(
////            path = "/json-upload",
////            consumes = MediaType.APPLICATION_JSON_VALUE,
////            produces = MediaType.APPLICATION_JSON_VALUE
////    )
////    @ResponseStatus(HttpStatus.CREATED)
////    public FileResponseDTO uploadJson(@RequestBody FileUploadRequestDTO dto) {
////        // 1) find user and course
////        var user = userService.getUserById(dto.userId())
////                .orElseThrow(() -> new ResponseStatusException(
////                        HttpStatus.NOT_FOUND, "User not found"));
////        var course = courseService.getCourseById(dto.courseId())
////                .orElseThrow(() -> new ResponseStatusException(
////                        HttpStatus.NOT_FOUND, "Course not found"));
////
////        // 2) build and save the File entity
////        var f = new File();
////        f.setUser(user);
////        f.setCourse(course);
////        f.setFile_name(dto.fileName());
////        f.setFile_type(dto.fileType());
////        f.setMd5("TODO-compute-md5"); // placeholder
////        f.setUpload_date(LocalDate.now());
////        f.setProcessed(false);
////
////        var saved = fileService.saveFile(f);
////
////        // 3) return a response DTO
////        return new FileResponseDTO(
////                saved.getId(),
////                user.getId(),
////                course.getId(),
////                saved.getFile_name(),
////                saved.getFile_type(),
////                saved.getUpload_date(),
////                saved.isProcessed()
////        );
////    }
//
//
//    //testing
////    @PostMapping("/upload")
////    public ResponseEntity<String> uploadFile() {
////        // Create a dummy/mock file
////        PythonService.MockFile file = new PythonService.MockFile("testfile.txt");
////
////        String response = pythonService.uploadFile(file);
////        return ResponseEntity.ok(response);
////    }
//
//
//
//
////    @PostMapping(consumes = "application/octet-stream")
////    @ResponseStatus(HttpStatus.CREATED)
////    public Map<String, Object> uploadFileRaw(
////            @RequestParam("courseId") Long courseId,
////            @RequestParam("userId") Long userId,
////            @RequestHeader("File-Name") String filename,
////            @RequestBody byte[] fileContent
////    ) throws Exception {
////        User user = userService.getUserById(userId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
////        Course course = courseService.getCourseById(courseId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
////
////        File f = new File();
////        f.setUser(user);
////        f.setCourse(course);
////        f.setFile_name(filename);
////        f.setFile_type("application/octet-stream");
////        f.setMd5(filename); // placeholder
////        f.setUpload_date(LocalDate.now());
////        f.setProcessed(false);
////
////        File saved = fileService.saveFile(f);
////
////        return Map.of(
////                "fileId", saved.getId(),
////                "filename", saved.getFile_name()
////        );
////    }
//
//
//
//
//
//
//    /**
//     * Upload a new file and attach it to a course.
//     * Testable via Postman as multipart/form-data.
//     */
////    @PostMapping
////    @ResponseStatus(HttpStatus.CREATED)
////    public Map<String,Object> create(
////            @RequestParam("courseId") Long courseId,
////            @RequestParam("userId")   Long userId,
////            @RequestParam("file")     MultipartFile file
////    ) throws Exception {
////
////        log.info("Received upload request: courseId={} userId={} fileName={} size={}",
////                courseId, userId, file.getOriginalFilename(), file.getSize());
////
////        // 1) Validate User
////        User user = userService.getUserById(userId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
////
////        // 2) Validate Course
////        Course course = courseService.getCourseById(courseId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
////
////        // 3) Create File entity
////        File f = new File();
////        f.setUser(user);
////        f.setCourse(course);
////        f.setFile_name(file.getOriginalFilename());
////        f.setFile_type(file.getContentType());
////        //f.setMd5(file.getOriginalFilename()); // placeholder for now
////        f.setMd5("TODO‑compute‑md5");
////        f.setUpload_date(LocalDate.now());
////        f.setProcessed(false);
////
////        File saved = fileService.saveFile(f);
////
////        // 4) Optional: Forward file to Python service
//////        Map<?, ?> pyResp = pythonService.uploadFile(file);
//////        String taskId = (String) pyResp.get("task_id");
////
////        // 5) Return response
//////        return Map.of(
//////                "fileId", saved.getId(),
//////                "filename", saved.getFile_name(),
//////                "taskId", taskId
//////        );
//////        return Map.of(
//////                "fileId",   saved.getId(),
//////                "filename", saved.getFile_name()
//////                // no taskId while Python is down
//////        );
////
////        log.info("Saved File metadata: id={} filename={}", saved.getId(), saved.getFile_name());
////        return Map.of("fileId", saved.getId());
////    }
//
//    /**
//     * Get processing status of a file by its Python task ID
//     */
////    @GetMapping("/status/{taskId}")
////    public Map<?, ?> checkStatus(@PathVariable String taskId) {
////        return pythonService.getTaskStatus(taskId);
////    }
////
////    /**
////     * List all files
////     */
////    @GetMapping
////    public List<Map<String, ?>> listAll()
////
////    {
////        return fileService.getAllFiles().stream()
////                .map(f -> Map.of(
////                        "id", f.getId(),
////                        "filename", f.getFile_name(),
////                        "user", f.getUser().getName(),
////                        "course", f.getCourse().getCourse_name(),
////                        "uploadDate", f.getUpload_date(),
////                        "processed", f.isProcessed()
////                )).collect(Collectors.toList());
////    }
//
//    /**
//     * Get a file by ID
//     */
////    @GetMapping("/{id}")
////    public Map<String,Object> getOne(@PathVariable Long id) {
////        File f = fileService.getFileById(id)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));
////        return Map.of(
////                "id", f.getId(),
////                "filename", f.getFile_name(),
////                "user", f.getUser().getName(),
////                "course", f.getCourse().getCourse_name(),
////                "uploadDate", f.getUpload_date(),
////                "processed", f.isProcessed()
////        );
////    }
////}
=======
//        File f = new File();
//        f.setUser(user);
//        f.setCourse(course);
//        f.setFile_name(filename);
//        f.setFile_type("application/octet-stream");
//        f.setMd5(filename); // placeholder
//        f.setUpload_date(LocalDate.now());
//        f.setProcessed(false);
=======
//
>>>>>>> 1742606 (changes with FileController and some processing ai files)
//
////@CrossOrigin
////@RestController
//////@RequestMapping("/api/files")
////@RequestMapping("/api/courses/{courseId}/files")
////public class FileController {
////
////
////    private final Logger log = LoggerFactory.getLogger(FileController.class);
////    private final FileService fileService;
////    private final UserService userService;
////    private final CourseService courseService;
////    private final PythonService pythonService;
////
////    public FileController(FileService fileService,
////                          UserService userService,
////                          CourseService courseService,
////                          PythonService pythonService) {
////        this.fileService = fileService;
////        this.userService = userService;
////        this.courseService = courseService;
////        this.pythonService = pythonService;
////    }
////
////
////
////    @PostMapping(consumes = "application/json", produces = "application/json")
////    @ResponseStatus(HttpStatus.CREATED)
////    public FileResponseDTO createJson(
////            @PathVariable Long courseId,
////            @RequestBody FileUploadRequestDTO dto
////    ) {
////        // 1) load course
////        Course course = courseService.getCourseById(courseId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
////        // 2) load user
////        User user = userService.getUserById(dto.userId())
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
////        // 3) build & save File
////        File f = new File();
////        f.setCourse(course);
////        f.setUser(user);
////        f.setFile_name(dto.fileName());
////        f.setFile_type(dto.fileType());
////        f.setMd5("TODO‑compute‑md5");
////        f.setUpload_date(LocalDate.now());
////        f.setProcessed(false);
////        File saved = fileService.saveFile(f);
////
////        // 4) return response DTO
////        return new FileResponseDTO(
////                saved.getId(),
////                user.getId(),
////                course.getId(),
////                saved.getFile_name(),
////                saved.getFile_type(),
////                saved.getUpload_date(),
////                saved.isProcessed()
////        );
////    }
////
////    /**
////     * List all files under this course.
////     */
////    @GetMapping(produces = "application/json")
////    public List<FileResponseDTO> listByCourse(@PathVariable Long courseId) {
////        Course course = courseService.getCourseById(courseId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
////        return fileService.getFilesByCourse(course)
////                .stream()
////                .map(f -> new FileResponseDTO(
////                        f.getId(),
////                        f.getUser().getId(),
//////                        course.getId(),
////                        courseId,
////                        f.getFile_name(),
////                        f.getFile_type(),
////                        f.getUpload_date(),
////                        f.isProcessed()
////                ))
////                .collect(Collectors.toList());
////    }
////}
//
//
//
//
////    @PostMapping(
////            path = "/json-upload",
////            consumes = MediaType.APPLICATION_JSON_VALUE,
////            produces = MediaType.APPLICATION_JSON_VALUE
////    )
////    @ResponseStatus(HttpStatus.CREATED)
////    public FileResponseDTO uploadJson(@RequestBody FileUploadRequestDTO dto) {
////        // 1) find user and course
////        var user = userService.getUserById(dto.userId())
////                .orElseThrow(() -> new ResponseStatusException(
////                        HttpStatus.NOT_FOUND, "User not found"));
////        var course = courseService.getCourseById(dto.courseId())
////                .orElseThrow(() -> new ResponseStatusException(
////                        HttpStatus.NOT_FOUND, "Course not found"));
////
////        // 2) build and save the File entity
////        var f = new File();
////        f.setUser(user);
////        f.setCourse(course);
////        f.setFile_name(dto.fileName());
////        f.setFile_type(dto.fileType());
////        f.setMd5("TODO-compute-md5"); // placeholder
////        f.setUpload_date(LocalDate.now());
////        f.setProcessed(false);
////
////        var saved = fileService.saveFile(f);
////
////        // 3) return a response DTO
////        return new FileResponseDTO(
////                saved.getId(),
////                user.getId(),
////                course.getId(),
////                saved.getFile_name(),
////                saved.getFile_type(),
////                saved.getUpload_date(),
////                saved.isProcessed()
////        );
////    }
//
//
//    //testing
////    @PostMapping("/upload")
////    public ResponseEntity<String> uploadFile() {
////        // Create a dummy/mock file
////        PythonService.MockFile file = new PythonService.MockFile("testfile.txt");
////
////        String response = pythonService.uploadFile(file);
////        return ResponseEntity.ok(response);
////    }
//
//
//
//
////    @PostMapping(consumes = "application/octet-stream")
////    @ResponseStatus(HttpStatus.CREATED)
////    public Map<String, Object> uploadFileRaw(
////            @RequestParam("courseId") Long courseId,
////            @RequestParam("userId") Long userId,
////            @RequestHeader("File-Name") String filename,
////            @RequestBody byte[] fileContent
////    ) throws Exception {
////        User user = userService.getUserById(userId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
////        Course course = courseService.getCourseById(courseId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
////
////        File f = new File();
////        f.setUser(user);
////        f.setCourse(course);
////        f.setFile_name(filename);
////        f.setFile_type("application/octet-stream");
////        f.setMd5(filename); // placeholder
////        f.setUpload_date(LocalDate.now());
////        f.setProcessed(false);
////
////        File saved = fileService.saveFile(f);
////
////        return Map.of(
////                "fileId", saved.getId(),
////                "filename", saved.getFile_name()
////        );
////    }
//
//
//
//
//
//
//    /**
//     * Upload a new file and attach it to a course.
//     * Testable via Postman as multipart/form-data.
//     */
////    @PostMapping
////    @ResponseStatus(HttpStatus.CREATED)
////    public Map<String,Object> create(
////            @RequestParam("courseId") Long courseId,
////            @RequestParam("userId")   Long userId,
////            @RequestParam("file")     MultipartFile file
////    ) throws Exception {
////
////        log.info("Received upload request: courseId={} userId={} fileName={} size={}",
////                courseId, userId, file.getOriginalFilename(), file.getSize());
////
////        // 1) Validate User
////        User user = userService.getUserById(userId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
////
////        // 2) Validate Course
////        Course course = courseService.getCourseById(courseId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
////
////        // 3) Create File entity
////        File f = new File();
////        f.setUser(user);
////        f.setCourse(course);
////        f.setFile_name(file.getOriginalFilename());
////        f.setFile_type(file.getContentType());
////        //f.setMd5(file.getOriginalFilename()); // placeholder for now
////        f.setMd5("TODO‑compute‑md5");
////        f.setUpload_date(LocalDate.now());
////        f.setProcessed(false);
////
////        File saved = fileService.saveFile(f);
////
////        // 4) Optional: Forward file to Python service
//////        Map<?, ?> pyResp = pythonService.uploadFile(file);
//////        String taskId = (String) pyResp.get("task_id");
////
////        // 5) Return response
//////        return Map.of(
//////                "fileId", saved.getId(),
//////                "filename", saved.getFile_name(),
//////                "taskId", taskId
//////        );
//////        return Map.of(
//////                "fileId",   saved.getId(),
//////                "filename", saved.getFile_name()
//////                // no taskId while Python is down
//////        );
////
////        log.info("Saved File metadata: id={} filename={}", saved.getId(), saved.getFile_name());
////        return Map.of("fileId", saved.getId());
////    }
//
<<<<<<< HEAD
//    {
//        return fileService.getAllFiles().stream()
//                .map(f -> Map.of(
//                        "id", f.getId(),
//                        "filename", f.getFile_name(),
//                        "user", f.getUser().getName(),
//                        "course", f.getCourse().getCourse_name(),
//                        "uploadDate", f.getUpload_date(),
//                        "processed", f.isProcessed()
//                )).collect(Collectors.toList());
//    }

    /**
     * Get a file by ID
     */
//    @GetMapping("/{id}")
//    public Map<String,Object> getOne(@PathVariable Long id) {
//        File f = fileService.getFileById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));
//        return Map.of(
//                "id", f.getId(),
//                "filename", f.getFile_name(),
//                "user", f.getUser().getName(),
//                "course", f.getCourse().getCourse_name(),
//                "uploadDate", f.getUpload_date(),
//                "processed", f.isProcessed()
//        );
//    }
//}
>>>>>>> cc5b632 (modified everything)
=======
//    /**
//     * Get processing status of a file by its Python task ID
//     */
////    @GetMapping("/status/{taskId}")
////    public Map<?, ?> checkStatus(@PathVariable String taskId) {
////        return pythonService.getTaskStatus(taskId);
////    }
////
////    /**
////     * List all files
////     */
////    @GetMapping
////    public List<Map<String, ?>> listAll()
////
////    {
////        return fileService.getAllFiles().stream()
////                .map(f -> Map.of(
////                        "id", f.getId(),
////                        "filename", f.getFile_name(),
////                        "user", f.getUser().getName(),
////                        "course", f.getCourse().getCourse_name(),
////                        "uploadDate", f.getUpload_date(),
////                        "processed", f.isProcessed()
////                )).collect(Collectors.toList());
////    }
//
//    /**
//     * Get a file by ID
//     */
////    @GetMapping("/{id}")
////    public Map<String,Object> getOne(@PathVariable Long id) {
////        File f = fileService.getFileById(id)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));
////        return Map.of(
////                "id", f.getId(),
////                "filename", f.getFile_name(),
////                "user", f.getUser().getName(),
////                "course", f.getCourse().getCourse_name(),
////                "uploadDate", f.getUpload_date(),
////                "processed", f.isProcessed()
////        );
////    }
////}
>>>>>>> 1742606 (changes with FileController and some processing ai files)
