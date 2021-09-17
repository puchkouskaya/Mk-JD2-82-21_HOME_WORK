package by.it_academy.jd2.Mk_JD2_82_21.view.storage;

import by.it_academy.jd2.Mk_JD2_82_21.view.FileUserService;
import by.it_academy.jd2.Mk_JD2_82_21.view.UserService;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.StorageType;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.UserWebApp;

public class UserStorageFactory {
    private static StorageType type = null;

    private UserStorageFactory() {
    }

    public static synchronized void setType(StorageType type) {
        if(type != null){
            UserStorageFactory.type = type;
        } else {
            throw new IllegalStateException("Нельзя менять тип хранилища");
        }
    }

    public static UserWebApp getInstance(){
        if(type == null){
            throw new IllegalStateException("Тип хранилища не задан");
        }
        switch (type) {
            case MEMORY:
                return UserService.getInstance ();
            case FILE:
                return FileUserService.getInstance ();
            default:
                throw new IllegalStateException("Неизвестный тип хранилища пользователей");
        }
    }
}
