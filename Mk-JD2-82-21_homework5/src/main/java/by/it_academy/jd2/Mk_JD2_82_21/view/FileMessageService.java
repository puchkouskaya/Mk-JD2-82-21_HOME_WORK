package by.it_academy.jd2.Mk_JD2_82_21.view;

import by.it_academy.jd2.Mk_JD2_82_21.model.Message;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.MessageWebApp;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.MessageStorage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FileMessageService implements MessageWebApp {
    private static FileMessageService instance = new FileMessageService();
    private static final String MESSAGE_FILE = "messages.ser";
    @Override
    public void addMessageStorage(String login, Message message) {
        if(MessageStorage.getInstance().getMessages ().isEmpty()) {
            MessageStorage.getInstance().getMessages ().putAll(getMessageMap());
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream (MESSAGE_FILE))) {
            MessageService.getInstance().addMessageStorage (login, message);
            oos.writeObject(MessageStorage.getInstance().getMessages ());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Override
    public LinkedList<Message> getMessageUser(String login) {
        return getMessageMap().get(login);
    }

    public Map<String,LinkedList<Message>> getMessageMap() {
        Map<String, LinkedList<Message>> messagesMap = new HashMap<> ();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream (MESSAGE_FILE))){
            messagesMap = (Map<String, LinkedList<Message>>) ois.readObject();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return messagesMap;
    }

    public static FileMessageService getInstance() {
        return instance;
    }
}
