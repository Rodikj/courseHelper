package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.QueryDTO;
import mk.ukim.finki.coursehelper.model.Query;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.service.QueryService;
import mk.ukim.finki.coursehelper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/queries")
public class QueryController {
    private final QueryService queryService;
    private final UserService userService;

    public QueryController(QueryService queryService, UserService userService) {
        this.queryService = queryService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QueryDTO create(@RequestBody QueryDTO dto) {
        User user = userService.getUserById(dto.user().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Query q = new Query();
        q.setUser(user);
        q.setQuery_text(dto.query_text());
        q.setTimestamp(LocalDateTime.now());
        Query saved = queryService.saveQuery(q);
        return new QueryDTO(
                saved.getId(),
                saved.getUser(),
                saved.getQuery_text(),
                saved.getTimestamp()
        );
    }

    @GetMapping
    public List<QueryDTO> listAll() {
        return queryService.getAllQueries().stream()
                .map(q -> new QueryDTO(
                        q.getId(),
                        q.getUser(),
                        q.getQuery_text(),
                        q.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
}
