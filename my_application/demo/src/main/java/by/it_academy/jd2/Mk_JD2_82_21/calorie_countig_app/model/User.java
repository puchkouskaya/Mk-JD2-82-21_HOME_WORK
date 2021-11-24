package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.api.ERoleUser;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.api.EStatusUser;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_user")
    private String name;

    @Column(name = "login_user")
    private String login;

    @Column(name = "password_user")
    private String password;

    @Column(name = "role_user")
    private ERoleUser role;

    @Column(name = "status_user")
    private EStatusUser statusUser;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreate;

    @Column
    @Version
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateUpdate;

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ERoleUser getRole() {
        return role;
    }

    public void setRole(ERoleUser role) {
        this.role = role;
    }

    public EStatusUser getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(EStatusUser statusUser) {
        this.statusUser = statusUser;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
