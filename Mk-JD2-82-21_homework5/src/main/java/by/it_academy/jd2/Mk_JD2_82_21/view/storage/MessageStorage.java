package by.it_academy.jd2.Mk_JD2_82_21.view.storage;

import by.it_academy.jd2.Mk_JD2_82_21.model.Message;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.MessageWebApp;

import java.util.*;

public class MessageStorage {
    private static final MessageStorage instance = new MessageStorage();
    private static Map<String, LinkedList<Message>> messages;

    private MessageStorage() {
        this.messages = new HashMap<> ();
    }


    public static Map<String, LinkedList<Message>> getMessages() {
        return messages;
    }

    public static MessageStorage getInstance() {
        return instance;
    }
}
