package by.it_academy.jd2.Mk_JD2_82_21.controller.filters;

import by.it_academy.jd2.Mk_JD2_82_21.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.UserStorageFactory;

import javax.servlet.*;
import java.io.IOException;

public class SignInFilter implements Filter {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    private FilterConfig filterConfig;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String login = servletRequest.getParameter(LOGIN_PARAM);
        String password = servletRequest.getParameter(PASSWORD_PARAM);

        User user = UserStorageFactory.getInstance ().authentication(login, password);

        if (user == null) {
            servletRequest.getRequestDispatcher("/views/signIn_exception.jsp").forward(servletRequest, servletResponse);
        } else {
            String userPassword = user.getPassword();
            if (!password.equals(userPassword)) {
                servletRequest.getRequestDispatcher("/views/signIn_exception.jsp").forward(servletRequest, servletResponse);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
