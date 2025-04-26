package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);

        // basic passowrd enc or jwt?
//        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, String name, String email, String password) {
        User user = getUserById(id).orElseThrow(() -> new RuntimeException("User id not found"));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }

}
