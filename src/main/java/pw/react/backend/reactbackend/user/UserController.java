package pw.react.backend.reactbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping(path = "")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok().body(userRepository.save(user));
    }

}
