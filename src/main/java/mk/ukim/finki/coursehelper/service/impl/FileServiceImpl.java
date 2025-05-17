package mk.ukim.finki.coursehelper.service.impl;

import mk.ukim.finki.coursehelper.model.Course;
import mk.ukim.finki.coursehelper.model.File;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.FileRepository;
import mk.ukim.finki.coursehelper.service.FileService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService
{

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository)
    {
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

    @Override
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public List<File> getFilesByCourse(Course course) {
        return fileRepository.findByCourse(course);
    }
}
