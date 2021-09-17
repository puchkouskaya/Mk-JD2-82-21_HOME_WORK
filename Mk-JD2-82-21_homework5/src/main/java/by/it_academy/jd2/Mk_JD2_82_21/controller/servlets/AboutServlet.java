package by.it_academy.jd2.Mk_JD2_82_21.controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet(name = "AboutServlet",urlPatterns = "/about")
public class AboutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String storageType = req.getServletContext().getInitParameter("storageType");
        req.setAttribute("storageType", storageType);

        LocalDateTime dateTime = (LocalDateTime) req.getServletContext().getAttribute("time");
        String time = dateTime.format(DateTimeFormatter.ofPattern("dd:MM:yyy в HH:mm"));
        req.setAttribute("time", time);

        req.getRequestDispatcher("/views/about.jsp").forward(req,resp);
    }
}
