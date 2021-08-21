package by.it_academy.jd2.Mk_JD2_82_21.view;

import by.it_academy.jd2.Mk_JD2_82_21.model.Message;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.MessageWebApp;

import java.util.*;

public class MessageStorage implements MessageWebApp {
    private static final MessageStorage instance = new MessageStorage();
    private final static Map<String, LinkedList<Message>> messages = new HashMap<> ();

    public void addMessageStorage(String login, Message message) {
        if(getMessages().containsKey(login)) {
            getMessages().get(login).add(message);
        } else {
            getMessages().put(login, new LinkedList<Message> ());
        }
    }
    public LinkedList<Message> getMessageUser(String login) {
        return getMessages().get(login);
    }

    public static Map<String, LinkedList<Message>> getMessages() {
        return messages;
    }

    public static MessageStorage getInstance() {
        return instance;
    }
}
