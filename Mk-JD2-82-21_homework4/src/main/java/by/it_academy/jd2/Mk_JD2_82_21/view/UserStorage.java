package by.it_academy.jd2.Mk_JD2_82_21.view;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.UserWebApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserStorage implements UserWebApp {
    private final static UserStorage instance = new UserStorage ();
    private Map<String, User> userMap = new HashMap<> ();

    public UserStorage() {
    }

    @Override
    public void registration(User user) {
        if (user == null) {
            throw new IllegalArgumentException ("Пользователь не создан !");
        }
        String login = user.getLogin();

        if (getUserMap().containsKey(login)) {
            throw new IllegalArgumentException("Пользователь с логином " + login + " уже сущуствует");
        }
        getUserMap().put(login, user);
    }

    @Override
    public User getUser(String login) {
        return getUserMap().get(login);
    }

    @Override
    public User authentication(String login, String password) {
        User user = getUser (login);
        if(user == null){
            return null;
        }
        if(!Objects.equals(user.getPassword(), password)){
            return null;
        }
        return user;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public static UserStorage getInstance() {
        return instance;
    }
}
