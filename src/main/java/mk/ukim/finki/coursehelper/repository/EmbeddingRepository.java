package mk.ukim.finki.coursehelper.repository;


import mk.ukim.finki.coursehelper.model.Embedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmbeddingRepository extends JpaRepository<Embedding, Long>
{

}
