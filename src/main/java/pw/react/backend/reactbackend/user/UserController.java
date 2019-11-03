package pw.react.backend.reactbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.exception.ResourceNotFoundException;
import pw.react.backend.reactbackend.user.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping(path = "")
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws ResourceNotFoundException {

        User user = userRepository.findById(userId).orElseThrow( () ->
            new ResourceNotFoundException(String.format("User with id [%s] not found.", userId))
        );

        return ResponseEntity.ok().body(user);
    }

    @PutMapping(path = "")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody @Valid User newUser) throws ResourceNotFoundException {

        User oldUser = userRepository.findById(userId).orElseThrow( () ->
                new ResourceNotFoundException(String.format("User with id [%s] not found.", userId))
        );

        oldUser.setLogin(newUser.getLogin());
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setBirthDate(newUser.getBirthDate());
        oldUser.setActive(newUser.getActive());

        final User updatedUser = userRepository.save(oldUser);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(path = "/{userId}")
    public Map<String, Boolean> deleteUser(@PathVariable Long userId)  throws ResourceNotFoundException{
        User user = userRepository.findById(userId).orElseThrow( () ->
                new ResourceNotFoundException(String.format("User with id [%s] not found.", userId))
        );

        userRepository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
