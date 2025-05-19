package mk.ukim.finki.coursehelper.repository;



import mk.ukim.finki.coursehelper.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {
    // you now have findById(id) returning either a File or a VideoLink
}

