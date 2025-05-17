package mk.ukim.finki.coursehelper.service.impl;

<<<<<<< HEAD
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.repository.UserRepository;
import mk.ukim.finki.coursehelper.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createUser(String name, String surname, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if(user != null && passwordEncoder.matches(rawPassword, user.getPassword())){
            return user;
        }
        throw new RuntimeException("Invalid email address or password");
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, String name, String surname, String email, String password) {
        User user = getUserById(id).orElseThrow(() -> new RuntimeException("User id not found"));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }
=======
public class UserServiceImpl {
>>>>>>> 3fc453b (updated user)
}
