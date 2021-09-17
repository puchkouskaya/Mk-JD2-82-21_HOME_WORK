package by.it_academy.jd2.Mk_JD2_82_21.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.model.Message;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.MessageStorageFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "ChatsServlet", urlPatterns = "/chats")
public class ChatsServlet extends HttpServlet {
    private static final String SESSION_ATTRIBUTE_PARAM_NAME = "user";

    public ChatsServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SESSION_ATTRIBUTE_PARAM_NAME);

        String login = user.getLogin();
        LinkedList<Message> messages = MessageStorageFactory.getInstance ().getMessageUser (login);

        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/views/chats.jsp").forward(req, resp);
    }
}
