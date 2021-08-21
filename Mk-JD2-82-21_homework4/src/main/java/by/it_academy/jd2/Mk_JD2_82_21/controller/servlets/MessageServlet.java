package by.it_academy.jd2.Mk_JD2_82_21.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.model.Message;
import by.it_academy.jd2.Mk_JD2_82_21.view.MessageStorage;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.MessageWebApp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {
    private static final String MESSAGE_PARAM = "message";
    private static final String SESSION_ATTRIBUTE_PARAM_NAME = "user";
    private static final String LOGIN_OF_RECIPIENT_PARAM_NAME = "recipient";
    private final MessageWebApp messageStorage;

    public MessageServlet() {
        this.messageStorage = MessageStorage.getInstance ();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User senderOfMessage = (User) session.getAttribute(SESSION_ATTRIBUTE_PARAM_NAME);

        String loginRecipient = req.getParameter(LOGIN_OF_RECIPIENT_PARAM_NAME);
        String message = req.getParameter(MESSAGE_PARAM);
        LocalDateTime date = LocalDateTime.now();

        Message userMessage = new Message ();
        userMessage.setMessage (message);
        userMessage.setSenderOfMessage (senderOfMessage);
        userMessage.setDate (date);

        messageStorage.addMessageStorage (loginRecipient, userMessage);

        req.getRequestDispatcher("/views/message1.jsp").forward(req, resp);
    }
}
