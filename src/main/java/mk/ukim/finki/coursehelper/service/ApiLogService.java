package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.API_Log;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.API_LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiLogService {
    private final API_LogRepository apiLogRepository;

    public ApiLogService(API_LogRepository apiLogRepository) {
        this.apiLogRepository = apiLogRepository;
    }

    public API_Log saveLog(API_Log log) {
        return apiLogRepository.save(log);
    }

    public Optional<API_Log> getLogById(Long id) {
        return apiLogRepository.findById(id);
    }

    public List<API_Log> getLogsForUser(User user) {
        return apiLogRepository.findByUser(user);
    }

    public void deleteLog(Long id) {
        apiLogRepository.deleteById(id);
    }
    public List<API_Log> getAllLogs() {
        return apiLogRepository.findAll();
    }
}
