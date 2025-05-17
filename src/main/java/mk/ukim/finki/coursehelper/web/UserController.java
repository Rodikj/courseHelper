package mk.ukim.finki.coursehelper.web;

import mk.ukim.finki.coursehelper.dto.*;
import mk.ukim.finki.coursehelper.model.User;
import mk.ukim.finki.coursehelper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerUser(@RequestBody RegisterDTO registerDTO) {
        try {
            User user = userService.createUser(registerDTO.name(), registerDTO.surname(), registerDTO.email(), registerDTO.password());

            RegisterResponseDTO responseDTO = new RegisterResponseDTO(
                    user.getId(),
                    user.getName(),
                    user.getSurname(),
                    user.getEmail()
            );

            return ResponseEntity.ok(responseDTO);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        try{
            User user = userService.login(loginDTO.email(), loginDTO.password());

            LoginResponseDTO responseDTO = new LoginResponseDTO(
                    user.getId(),
                    user.getEmail()
            );

            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
