package pw.react.backend.reactbackend.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pw.react.backend.reactbackend.utils.JsonDateDeserializer;
import pw.react.backend.reactbackend.utils.JsonDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String login;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDate birthDate;
    @Column
    private boolean active;

    public long getId() { return id; }
    public String getLogin() { return login; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public boolean getActive() { return active; }

    public void setId(long id) { this.id = id; }
    public void setLogin(String login) { this.login = login; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setActive(Boolean active) { this.active = active; }

    public User(String login, String firstName, String lastName, boolean active) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    public User() {};
}
