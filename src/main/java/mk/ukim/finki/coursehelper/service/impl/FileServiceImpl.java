<<<<<<< HEAD
//package mk.ukim.finki.coursehelper.service.impl;
//
//import mk.ukim.finki.coursehelper.model.Course;
//import mk.ukim.finki.coursehelper.model.File;
//import mk.ukim.finki.coursehelper.model.User;
//import mk.ukim.finki.coursehelper.repository.FileRepository;
//import mk.ukim.finki.coursehelper.service.FileService;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class FileServiceImpl implements FileService
//{
//
//    private final FileRepository fileRepository;
//
//    public FileServiceImpl(FileRepository fileRepository)
//    {
//        this.fileRepository = fileRepository;
//    }
//
//    @Override
//    public File saveFile(File file) {
//        return fileRepository.save(file);
//    }
//
//    @Override
//    public Optional<File> getFileById(Long id) {
//        return fileRepository.findById(id);
//    }
//
//    @Override
//    public List<File> getFilesByUser(User user) {
//        return fileRepository.findByUser(user);
//    }
//
//    @Override
//    public File updateFile(Long id, User user, Course course, String md5, String file_name, String file_type, LocalDate upload_date, boolean processed) {
//        File file = getFileById(id).orElseThrow(() -> new RuntimeException("User id not found"));
//        file.setUser(user);
//        file.setCourse(course);
//        file.setMd5(md5);
//        file.setFileName(file_name);
//        file.setFileType(file_type);
//        file.setUpload_date(upload_date);
//        file.setProcessed(processed);
//        return fileRepository.save(file);
//    }
//
//    @Override
//    public List<File> getAllFiles() {
//        return fileRepository.findAll();
//    }
//
//    @Override
//    public List<File> getFilesByCourse(Course course) {
//        return fileRepository.findByCourse(course);
//    }
//}


package mk.ukim.finki.coursehelper.service.impl;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.File;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.FileRepository;
import mk.ukim.finki.coursehelper.service.FileService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public Optional<File> getFileById(Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public List<File> getFilesByUser(User user) {
        return fileRepository.findByUser(user);
    }

    @Override
    public File updateFile(Long id, User user, Course course,
                           String md5, String fileName,
                           String fileType, LocalDate uploadDate,
                           boolean processed) {
        File f = fileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No such file"));
        f.setUser(user);
        f.setCourse(course);
        f.setMd5(md5);
        f.setFileName(fileName);
        f.setFileType(fileType);
        f.setUploadDate(uploadDate);
        f.setProcessed(processed);
        return fileRepository.save(f);
    }

    @Override
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public List<File> getFilesByCourse(Course course) {
        return fileRepository.findByCourse(course);
    }

    @Override
    public File storeFile(User user,
                          Course course,
                          MultipartFile multipartFile,
                          String storageLocation) throws IOException {
        // 1) Ensure base folder exists
        Path storagePath = Paths.get(storageLocation).toAbsolutePath().normalize();
        Files.createDirectories(storagePath);

        // 2) Read bytes & compute MD5
        byte[] bytes = multipartFile.getBytes();
        String md5 = DigestUtils.md5Hex(bytes);

        // 3) Build a unique on‑disk name
        String cleanName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String diskName   = md5 + "_" + cleanName;
        Path target       = storagePath.resolve(diskName);

        // 4) Write bytes
        Files.write(target, bytes, StandardOpenOption.CREATE);

        // 5) Persist metadata
        File file = new File();
        file.setUser(user);
        file.setCourse(course);
        file.setMd5(md5);
        file.setFileName(cleanName);
        file.setFileType(multipartFile.getContentType());
        file.setUploadDate(LocalDate.now());
        file.setProcessed(false);

        return fileRepository.save(file);
    }
=======
package mk.ukim.finki.coursehelper.service.impl;

public class FileServiceImpl {
>>>>>>> 3fc453b (updated user)
}
