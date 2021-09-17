package by.it_academy.jd2.Mk_JD2_82_21.view.storage;

import by.it_academy.jd2.Mk_JD2_82_21.view.FileMessageService;
import by.it_academy.jd2.Mk_JD2_82_21.view.MessageService;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.MessageWebApp;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.StorageType;

public class MessageStorageFactory {
    private static StorageType type = null;

    private MessageStorageFactory() {
    }

    public static synchronized void setType(StorageType type) {
        if(type != null){
            MessageStorageFactory.type = type;
        } else {
            throw new IllegalStateException("Нельзя менять тип хранилища");
        }
    }

    public static MessageWebApp getInstance(){
        if(type == null){
            throw new IllegalStateException("Тип хранилища не задан");
        }

        switch (type){
            case MEMORY:
                return MessageService.getInstance();
            case FILE:
                return FileMessageService.getInstance();
            default:
                throw new IllegalStateException("Неизвестный тип хранилища сообщений");
        }
    }
}
