package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.APILogDTO;
import mk.ukim.finki.coursehelper.model.API_Log;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.service.ApiLogService;
import mk.ukim.finki.coursehelper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/logs")
public class ApiLogController {
    private final ApiLogService logService;
    private final UserService userService;

    public ApiLogController(ApiLogService logService, UserService userService) {
        this.logService = logService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public APILogDTO create(@RequestBody APILogDTO dto) {
        User user = userService.getUserById(dto.user().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        API_Log log = new API_Log();
        log.setUser(user);
        log.setMethod(dto.method());
        log.setStatus_code(dto.status());
        log.setTimestamp(LocalDateTime.now());
        API_Log saved = logService.saveLog(log);
        return new APILogDTO(
                saved.getId(),
                saved.getUser(),
                saved.getMethod(),
                saved.getStatus_code(),
                saved.getTimestamp()
        );
    }

    @GetMapping
    public List<APILogDTO> listAll() {
        return logService.getAllLogs().stream()
                .map(l -> new APILogDTO(
                        l.getId(),
                        l.getUser(),
                        l.getMethod(),
                        l.getStatus_code(),
                        l.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
}
