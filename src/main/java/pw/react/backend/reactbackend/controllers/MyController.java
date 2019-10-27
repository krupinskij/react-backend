package pw.react.backend.reactbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pw.react.backend.reactbackend.models.MyEntity;
import pw.react.backend.reactbackend.respositories.MyRepository;
import pw.react.backend.reactbackend.respositories.SystemRepository;
import pw.react.backend.reactbackend.services.MyService;

@RestController
public class MyController {

    @Autowired
    MyRepository repository;
    @Autowired
    MyService service;

    @PostMapping("/create")
    public void createUser(@RequestBody MyEntity _user){
        MyEntity user = repository.findUserByLogin(_user.getLogin());

        if(user==null) {
            repository.save(new MyEntity(
                    _user.getLogin(),
                    _user.getFirstName(),
                    _user.getLastName(),
                    _user.getBirthDate(),
                    _user.isActive())
            );
        }
    }
}
