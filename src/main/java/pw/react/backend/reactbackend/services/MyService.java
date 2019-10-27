package pw.react.backend.reactbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import pw.react.backend.reactbackend.models.MyEntity;
import pw.react.backend.reactbackend.respositories.MyRepository;
import pw.react.backend.reactbackend.respositories.SystemRepository;

import java.util.ArrayList;
import java.util.List;

public class MyService {
    @Autowired
    private MyRepository repository;

    public MyEntity checkUser(String _login) {
        Object users = (Object)repository.findAll();
        ArrayList<MyEntity> u = (ArrayList<MyEntity>)users;
        for (MyEntity user : u) {
            if (user.getLogin()!=null && user.getLogin().equals(_login))
                return user;
        }
        return null;
    }
}
