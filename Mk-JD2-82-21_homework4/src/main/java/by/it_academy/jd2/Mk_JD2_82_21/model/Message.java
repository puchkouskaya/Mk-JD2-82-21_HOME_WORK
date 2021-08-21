package by.it_academy.jd2.Mk_JD2_82_21.model;

import java.time.LocalDateTime;

public class Message {
    private String message;
    private User senderOfMessage;
    private LocalDateTime date;

    public String getMessage() {
        return message;
    }

    public User getSenderOfMessage() {
        return senderOfMessage;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSenderOfMessage(User senderOfMessage) {
        this.senderOfMessage = senderOfMessage;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
