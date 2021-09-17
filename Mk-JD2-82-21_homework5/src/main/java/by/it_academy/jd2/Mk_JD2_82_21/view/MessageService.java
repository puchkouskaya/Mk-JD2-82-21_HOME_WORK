package by.it_academy.jd2.Mk_JD2_82_21.view;

import by.it_academy.jd2.Mk_JD2_82_21.model.Message;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.MessageWebApp;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.MessageStorage;

import java.util.LinkedList;

public class MessageService implements MessageWebApp {
    private final static MessageService instance = new MessageService ();


    private MessageService() {

    }

    @Override
    public void addMessageStorage(String login, Message message) {
        if(MessageStorage.getInstance ().getMessages().containsKey(login)) {
            MessageStorage.getInstance ().getMessages().get(login).add(message);
        } else {
            MessageStorage.getInstance ().getMessages().put(login, new LinkedList<Message> ());
        }
    }

    @Override
    public LinkedList<Message> getMessageUser(String login) {
        return MessageStorage.getInstance ().getMessages().get(login);
    }

    public static MessageService getInstance() {
        return instance;
    }
}
