package mk.ukim.finki.coursehelper.repository;

import mk.ukim.finki.coursehelper.model.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long>
{

}
