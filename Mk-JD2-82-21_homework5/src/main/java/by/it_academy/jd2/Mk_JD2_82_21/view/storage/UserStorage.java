package by.it_academy.jd2.Mk_JD2_82_21.view.storage;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.UserWebApp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserStorage {
    private final static UserStorage instance = new UserStorage ();
    private Map<String, User> userMap;

    private UserStorage() {
        this.userMap = new HashMap<> ();
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public static UserStorage getInstance() {
        return instance;
    }
}

