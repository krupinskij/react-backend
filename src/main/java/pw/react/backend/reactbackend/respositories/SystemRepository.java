package pw.react.backend.reactbackend.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.react.backend.reactbackend.models.MyEntity;

@Repository
public interface SystemRepository extends CrudRepository<System,Long> {
    MyEntity findUserByLogin(String login);
}
