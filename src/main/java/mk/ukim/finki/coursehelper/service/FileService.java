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

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.File;
import mk.ukim.finki.coursehelper.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    /**
     * Store the multipart file’s bytes on disk (under storageLocation),
     * compute MD5, and save metadata.
     */
    File storeFile(User user,
                   Course course,
                   MultipartFile multipartFile,
                   String storageLocation) throws IOException;
}
