package mk.ukim.finki.coursehelper.repository;

import mk.ukim.finki.coursehelper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

}
