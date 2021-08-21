package by.it_academy.jd2.Mk_JD2_82_21.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.view.UserStorage;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.UserWebApp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String FIO_PARAM = "fio";
    private static final String DATE_PARAM = "birthday";

    private final UserWebApp userStorage;

    public SignUpServlet() {
        this.userStorage = UserStorage.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        user.setLogin (req.getParameter(LOGIN_PARAM));
        user.setPassword (req.getParameter(PASSWORD_PARAM));
        user.setFio (req.getParameter(FIO_PARAM));
        user.setDate (req.getParameter (DATE_PARAM));

        if (req.getParameter(LOGIN_PARAM) == "" || req.getParameter(PASSWORD_PARAM) == ""
                || req.getParameter(FIO_PARAM) == "" || req.getParameter(DATE_PARAM) == "") {
            req.getRequestDispatcher("/signUp_exception.jsp").forward(req, resp);
        }
        if (UserStorage.getInstance().getUser(req.getParameter(LOGIN_PARAM)) != null) {
            req.getRequestDispatcher("/signUp_user_exception.jsp").forward(req, resp);
        }

        userStorage.registration (user);
        req.getSession().setAttribute("user", user);
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
}
