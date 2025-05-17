package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> f93b757 (some reshuffling with service more like wp with impl)
    User createUser(String name, String surname, String email, String password);
    User login(String email, String rawPassword);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, String name, String surname, String email, String password);
<<<<<<< HEAD
=======
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String name,String surname, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if(user != null && passwordEncoder.matches(rawPassword, user.getPassword())){
            return user;
        }
        throw new RuntimeException("Invalid email address or password");
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, String name, String surname, String email, String password) {
        User user = getUserById(id).orElseThrow(() -> new RuntimeException("User id not found"));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }
>>>>>>> 3fc453b (updated user)
=======
>>>>>>> f93b757 (some reshuffling with service more like wp with impl)

}
