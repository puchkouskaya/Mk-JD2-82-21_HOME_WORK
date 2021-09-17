package by.it_academy.jd2.Mk_JD2_82_21.view;


import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.UserStorage;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.UserWebApp;

import java.util.*;

public class UserService implements UserWebApp {
    private final static UserService instance = new UserService ();
    private UserStorage userStorage;


    private UserService() {
    }

    @Override
    public void registration(User user) {
        if (user == null) {
            throw new IllegalArgumentException ("Пользователь не создан!");
        }
        String login = user.getLogin();

        if (UserStorage.getInstance().getUserMap().containsKey(login)) {
            throw new IllegalArgumentException("Пользователь с логином " + login + " уже сущуствует");
        }
        UserStorage.getInstance().getUserMap().put(login, user);
    }

    @Override
    public User getUser(String login) {
        return UserStorage.getInstance().getUserMap().get(login);
    }

    @Override
    public User authentication(String login, String password) {
        User user = getUser (login);
        if(user == null) {
            return null;
        }
        if(!Objects.equals(user.getPassword(), password)){
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<> ();
        Map<String, User> users = UserStorage.getInstance().getUserMap();
        for(Map.Entry<String,User> item : users.entrySet()){
            allUsers.add(item.getValue());
        }
        return allUsers;
    }

    public Map<String,User> getMapUser() {
        return UserStorage.getInstance().getUserMap ();
    }


    public static UserService getInstance() {
        return instance;
    }
}
