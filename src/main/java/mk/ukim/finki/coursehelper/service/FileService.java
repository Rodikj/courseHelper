//package mk.ukim.finki.coursehelper.service;
//
//import jakarta.persistence.ManyToOne;
//import lombok.AllArgsConstructor;
//import mk.ukim.finki.coursehelper.model.Course;
//import mk.ukim.finki.coursehelper.model.File;
//import mk.ukim.finki.coursehelper.model.User;
//import mk.ukim.finki.coursehelper.repository.FileRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//
//public interface FileService {
//
//    File saveFile(File file);
//    Optional<File> getFileById(Long id);
//    List<File> getFilesByUser(User user);
//    File updateFile(Long id, User user, Course course, String md5, String file_name, String file_type, LocalDate upload_date, boolean processed);
//    List<File> getAllFiles();
//    List<File> getFilesByCourse(Course course);
//
//
//
//}

package mk.ukim.finki.coursehelper.service;

<<<<<<< HEAD
=======
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
>>>>>>> cc5b632 (modified everything)
import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.File;
import mk.ukim.finki.coursehelper.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
<<<<<<< HEAD
public interface FileService {
    File saveFile(File file);
    Optional<File> getFileById(Long id);
    List<File> getFilesByUser(User user);
    File updateFile(Long id, User user, Course course,
                    String md5, String fileName,
                    String fileType, LocalDate uploadDate,
                    boolean processed);
    List<File> getAllFiles();
    List<File> getFilesByCourse(Course course);
=======
@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    public Optional<File> getFileById(Long id) {
        return fileRepository.findById(id);
    }

    public List<File> getFilesByUser(User user) {
        return fileRepository.findByUser(user);
    }

    @ManyToOne
    private Course course;

    public File updateFile(Long id, User user, Course course, String md5, String file_name, String file_type, LocalDate upload_date, boolean processed) {
        File file = getFileById(id).orElseThrow(() -> new RuntimeException("User id not found"));
        file.setUser(user);
        file.setCourse(course);
        file.setMd5(md5);
        file.setFile_name(file_name);
        file.setFile_type(file_type);
        file.setUpload_date(upload_date);
        file.setProcessed(processed);
        return fileRepository.save(file);
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    // src/main/java/mk/ukim/finki/coursehelper/service/FileService.java
    public List<File> getFilesByCourse(Course course) {
        return fileRepository.findByCourse(course);
    }
=======
>>>>>>> f93b757 (some reshuffling with service more like wp with impl)

public interface FileService {

    File saveFile(File file);
    Optional<File> getFileById(Long id);
    List<File> getFilesByUser(User user);
    File updateFile(Long id, User user, Course course, String md5, String file_name, String file_type, LocalDate upload_date, boolean processed);
    List<File> getAllFiles();
    List<File> getFilesByCourse(Course course);


>>>>>>> cc5b632 (modified everything)

    /**
     * Store the multipart file’s bytes on disk (under storageLocation),
     * compute MD5, and save metadata.
     */
    File storeFile(User user,
                   Course course,
                   MultipartFile multipartFile,
                   String storageLocation) throws IOException;
}
