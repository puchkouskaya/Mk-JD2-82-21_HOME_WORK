package by.it_academy.jd2.Mk_JD2_82_21.view.api;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;

import java.util.Collection;

public interface UserWebApp {
    void registration(User user);
    User getUser(String login);
    User authentication(String login, String password);
    Collection<User> getAllUsers();
}
