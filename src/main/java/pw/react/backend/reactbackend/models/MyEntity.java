package pw.react.backend.reactbackend.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Users")
public class MyEntity {



    @Column(name = "id")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "isActive")
    private boolean isActive;

    public MyEntity(String _login, String _firstName, String _lastName, Date _birthDate, boolean _isActive) {
        this.login = _login;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.birthDate = _birthDate;
        this.isActive = _isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String _login) {
        login = login;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String _firstName) {
        firstName = _firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String _lastName) {
        lastName = _lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date _birthDate) {
        birthDate = _birthDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean _isActive) {
        isActive = _isActive;
    }
}
