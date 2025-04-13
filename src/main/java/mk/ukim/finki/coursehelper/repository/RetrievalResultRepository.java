package mk.ukim.finki.coursehelper.repository;


import mk.ukim.finki.coursehelper.model.RetrievalResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetrievalResultRepository extends JpaRepository<RetrievalResult, Long>
{

}
