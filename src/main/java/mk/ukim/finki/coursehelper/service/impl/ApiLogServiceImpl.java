package mk.ukim.finki.coursehelper.service.impl;

import mk.ukim.finki.coursehelper.model.API_Log;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.API_LogRepository;
import mk.ukim.finki.coursehelper.service.ApiLogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiLogServiceImpl implements ApiLogService
{

    private final API_LogRepository apiLogRepository;

        public ApiLogServiceImpl(API_LogRepository apiLogRepository)
        {
        this.apiLogRepository = apiLogRepository;
        }

    @Override
    public API_Log saveLog(API_Log log) {
        return apiLogRepository.save(log);
    }

    @Override
    public Optional<API_Log> getLogById(Long id) {
        return apiLogRepository.findById(id);
    }

    @Override
    public List<API_Log> getLogsForUser(User user) {
        return apiLogRepository.findByUser(user);
    }

    @Override
    public void deleteLog(Long id) {
        apiLogRepository.deleteById(id);
    }

    @Override
    public List<API_Log> getAllLogs() {
        return apiLogRepository.findAll();
    }
}
