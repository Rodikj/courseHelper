package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    User createUser(String name, String surname, String email, String password);
    User login(String email, String rawPassword);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, String name, String surname, String email, String password);

}
