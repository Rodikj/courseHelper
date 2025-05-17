package mk.ukim.finki.coursehelper.service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface PythonService
{

    Map<?, ?> uploadFile(MultipartFile file) throws Exception;
    Map<?, ?> getTaskStatus(String taskId);
}

