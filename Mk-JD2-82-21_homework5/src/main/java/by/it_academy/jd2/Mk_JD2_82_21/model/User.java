package by.it_academy.jd2.Mk_JD2_82_21.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    private String login;
    private String password;
    private String fio;
    private String dateOfBirth;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFio() {
        return fio;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
