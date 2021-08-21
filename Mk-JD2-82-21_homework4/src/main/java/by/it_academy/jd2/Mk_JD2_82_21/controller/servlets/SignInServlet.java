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

@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private final UserWebApp userStorage;

    public SignInServlet() {
        this.userStorage = UserStorage.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);

        User user = userStorage.authentication(login, password);

        if (user == null) {
            req.getRequestDispatcher("/signIn_exception.jsp").forward(req, resp);
        } else {
            String userPassword = user.getPassword ();
            if (!password.equals (userPassword)) {
                req.getRequestDispatcher ("/signIn_exception.jsp").forward (req, resp);
            }
        }

        req.getSession().setAttribute("user", user);
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);

    }
}
