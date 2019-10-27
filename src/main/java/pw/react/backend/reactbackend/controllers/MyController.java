package pw.react.backend.reactbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pw.react.backend.reactbackend.models.MyEntity;
import pw.react.backend.reactbackend.respositories.MyRepository;
import pw.react.backend.reactbackend.respositories.SystemRepository;
import pw.react.backend.reactbackend.services.MyService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MyController {

    @Autowired
    MyRepository repository;
    @Autowired
    MyService service;

    HttpServletResponse response;

    @PostMapping("/create")
    public <T> T createUser(@RequestBody MyEntity _user, HttpServletResponse res){
        MyEntity user = repository.findUserByLogin(_user.getLogin());

        if(user==null) {
            repository.save(new MyEntity(
                    _user.getLogin(),
                    _user.getFirstName(),
                    _user.getLastName(),
                    _user.getBirthDate(),
                    _user.isActive())
            );

            response = res;
            response.setStatus(200);
            return (T)"Ok!";
        }

        response = res;
        response.setStatus(402);
        return (T)"User exist. Payment required!";
    }

    @GetMapping("/retrieve/{login}")
    public <T> T getUser(@PathVariable String login, HttpServletResponse res) {
        MyEntity user = repository.findUserByLogin(login);

        if(user!=null) {
            response = res;
            response.setStatus(200);
            return (T)user;

        }

        response = res;
        response.setStatus(404);
        return (T)"User not found";
    }

    @PostMapping("/update/{login}")
    public <T> T updateUser(@PathVariable String login, @RequestBody MyEntity _user, HttpServletResponse res) {
        MyEntity user = repository.findUserByLogin(login);

        if(user==null) {
            response = res;
            response.setStatus(404);
            return (T)"User not found";
        }

        user.setLogin(_user.getLogin());
        user.setFirstName(_user.getFirstName());
        user.setLastName(_user.getLastName());
        user.setBirthDate(_user.getBirthDate());
        user.setIsActive(_user.isActive());

        response = res;
        response.setStatus(200);
        return (T)"OK!";

    }

    @DeleteMapping("/delete/{login}")
    public <T> T deleteUser(@PathVariable String login, HttpServletResponse res) {
        MyEntity user = repository.findUserByLogin(login);

        if(user==null) {
            response = res;
            response.setStatus(404);
            return (T)"User not found";
        }

        repository.delete(user);
        response = res;
        response.setStatus(200);
        return (T)"W porząsiu";

    }


}
