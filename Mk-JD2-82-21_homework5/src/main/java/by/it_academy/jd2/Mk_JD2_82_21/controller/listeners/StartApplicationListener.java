package by.it_academy.jd2.Mk_JD2_82_21.controller.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;

public class StartApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LocalDateTime time = LocalDateTime.now();
        servletContextEvent.getServletContext().setAttribute("time", time);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
