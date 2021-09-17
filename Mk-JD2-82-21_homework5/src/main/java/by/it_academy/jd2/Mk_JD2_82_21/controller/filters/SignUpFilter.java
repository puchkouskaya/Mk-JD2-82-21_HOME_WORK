package by.it_academy.jd2.Mk_JD2_82_21.controller.filters;

import by.it_academy.jd2.Mk_JD2_82_21.view.UserService;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.UserStorageFactory;

import javax.servlet.*;
import java.io.IOException;

public class SignUpFilter implements Filter {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String FIO_PARAM = "fio";
    private static final String DATE_PARAM = "birthday";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter(LOGIN_PARAM) == "" || servletRequest.getParameter(PASSWORD_PARAM) == ""
                || servletRequest.getParameter(FIO_PARAM) == "" || servletRequest.getParameter(DATE_PARAM) == "") {
            servletRequest.getRequestDispatcher("/views/signUp_exception.jsp").forward(servletRequest, servletResponse);
        } if (UserStorageFactory.getInstance().getUser(servletRequest.getParameter(LOGIN_PARAM)) != null) {
            servletRequest.getRequestDispatcher("/views/signUp_user_exception.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter (servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
