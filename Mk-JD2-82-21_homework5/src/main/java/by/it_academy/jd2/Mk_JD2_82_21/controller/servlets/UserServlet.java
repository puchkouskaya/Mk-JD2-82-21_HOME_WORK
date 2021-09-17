package by.it_academy.jd2.Mk_JD2_82_21.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.view.UserService;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.UserStorage;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.UserWebApp;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.UserStorageFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    public UserServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = (List<User>) UserStorageFactory.getInstance ().getAllUsers();
        req.setAttribute("allUsers",allUsers);
        req.getRequestDispatcher("/views/users.jsp").forward(req, resp);
    }
}
