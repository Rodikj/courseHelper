<<<<<<< HEAD
// package mk.ukim.finki.coursehelper.service.impl;


// import mk.ukim.finki.coursehelper.model.Query;
// import mk.ukim.finki.coursehelper.model.User;
// import mk.ukim.finki.coursehelper.repository.QueryRepository;
// import mk.ukim.finki.coursehelper.service.QueryService;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class QueryServiceImpl implements QueryService
// {
//     private final QueryRepository queryRepository;

//     public QueryServiceImpl(QueryRepository queryRepository) {
//         this.queryRepository = queryRepository;
//     }


//     @Override
//     public Query saveQuery(Query query) {
//         return queryRepository.save(query);
//     }

//     @Override
//     public Optional<Query> getQueryById(Long id) {
//         return queryRepository.findById(id);
//     }

//     @Override
//     public List<Query> getQueriesForUser(User user) {
//         return queryRepository.findByUserOrderByTimestampDesc(user);
//     }

//     @Override
//     public void deleteQuery(Long id) {
//         queryRepository.deleteById(id);
//     }

//     @Override
//     public List<Query> getAllQueries() {
//         return queryRepository.findAll();
//     }
// }
=======
package mk.ukim.finki.coursehelper.service.impl;


import mk.ukim.finki.coursehelper.model.Query;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.QueryRepository;
import mk.ukim.finki.coursehelper.service.QueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryServiceImpl implements QueryService
{
    private final QueryRepository queryRepository;

    public QueryServiceImpl(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }


    @Override
    public Query saveQuery(Query query) {
        return queryRepository.save(query);
    }

    @Override
    public Optional<Query> getQueryById(Long id) {
        return queryRepository.findById(id);
    }

    @Override
    public List<Query> getQueriesForUser(User user) {
        return queryRepository.findByUserOrderByTimestampDesc(user);
    }

    @Override
    public void deleteQuery(Long id) {
        queryRepository.deleteById(id);
    }

    @Override
    public List<Query> getAllQueries() {
        return queryRepository.findAll();
    }
}
>>>>>>> 3fc453b (updated user)
