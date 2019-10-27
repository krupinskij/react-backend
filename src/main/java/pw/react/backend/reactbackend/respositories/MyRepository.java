package pw.react.backend.reactbackend.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import pw.react.backend.reactbackend.models.MyEntity;
import pw.react.backend.reactbackend.services.MyService;

import java.util.ArrayList;
import java.util.Optional;

public abstract class MyRepository implements SystemRepository {

    @Autowired
    MyService service;

    @Override
    public MyEntity findUserByLogin(String _login) {
        return service.checkUser(_login);
    }

    public void save(MyEntity user) {
    }

    public void delete(MyEntity user) {

    }

    @Override
    public <S extends System> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<System> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<System> findAll() {
        return null;
    }

    @Override
    public Iterable<System> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(System system) {

    }

    @Override
    public void deleteAll(Iterable<? extends System> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
