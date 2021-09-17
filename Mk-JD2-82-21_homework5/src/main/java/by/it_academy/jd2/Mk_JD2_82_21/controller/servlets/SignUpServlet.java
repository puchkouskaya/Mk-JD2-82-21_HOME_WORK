package by.it_academy.jd2.Mk_JD2_82_21.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.view.UserService;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.UserStorageFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String FIO_PARAM = "fio";
    private static final String DATE_PARAM = "birthday";

    public SignUpServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLogin (req.getParameter(LOGIN_PARAM));
        user.setPassword (req.getParameter(PASSWORD_PARAM));
        user.setFio (req.getParameter(FIO_PARAM));
        user.setDateOfBirth (req.getParameter (DATE_PARAM));

        UserStorageFactory.getInstance().registration (user);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
}
