package pw.react.backend.reactbackend.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.exception.ResourceNotFoundException;
import pw.react.backend.reactbackend.user.User;
import pw.react.backend.reactbackend.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean loginExists(String login) throws ResourceNotFoundException {
        Optional<User> existingUser = userRepository.findByLogin(login);

        if(!existingUser.isPresent()) {
            throw new ResourceNotFoundException(String.format("User with login [%s] not found.", login));
        }

        return true;
    }

    @Override
    public boolean userExists(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());

        if(!existingUser.isPresent()) {
            return false;
        }

        return true;
    }
}
