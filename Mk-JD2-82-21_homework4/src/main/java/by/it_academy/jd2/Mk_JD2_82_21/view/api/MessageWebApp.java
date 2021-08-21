package by.it_academy.jd2.Mk_JD2_82_21.view.api;

import by.it_academy.jd2.Mk_JD2_82_21.model.Message;

import java.util.LinkedList;

public interface MessageWebApp {
   void addMessageStorage(String login, Message message);
    LinkedList<Message> getMessageUser(String login);
}
