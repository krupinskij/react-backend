package pw.react.backend.reactbackend.user.service;


import pw.react.backend.reactbackend.user.User;

public interface UserService {
    boolean loginExists(String login);
    boolean userExists(User user);
}
