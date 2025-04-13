package mk.ukim.finki.coursehelper.repository;

import mk.ukim.finki.coursehelper.model.API_Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface API_LogRepository extends JpaRepository<API_Log, Long>
{

}
