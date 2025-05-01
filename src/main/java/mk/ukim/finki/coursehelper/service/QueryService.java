package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.Query;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.QueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryService {
    private final QueryRepository queryRepository;

    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public Query saveQuery(Query query) {
        return queryRepository.save(query);
    }

    public Optional<Query> getQueryById(Long id) {
        return queryRepository.findById(id);
    }

    public List<Query> getQueriesForUser(User user) {
        return queryRepository.findByUserOrderByTimestampDesc(user);
    }

    public void deleteQuery(Long id) {
        queryRepository.deleteById(id);
    }

    public List<Query> getAllQueries() {
        return queryRepository.findAll();
    }
}
