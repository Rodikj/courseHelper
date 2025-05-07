package mk.ukim.finki.coursehelper.repository;

import mk.ukim.finki.coursehelper.model.Query;
import mk.ukim.finki.coursehelper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
    List<Query> findByUserOrderByTimestampDesc(User user);
}

