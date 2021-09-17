package by.it_academy.jd2.Mk_JD2_82_21.controller.listeners;

import by.it_academy.jd2.Mk_JD2_82_21.view.storage.MessageStorageFactory;
import by.it_academy.jd2.Mk_JD2_82_21.view.storage.UserStorageFactory;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.StorageType;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TypeStorageListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String storageTypeRaw = servletContextEvent.getServletContext().getInitParameter("storageType");

        StorageType storageType = StorageType.valueOf(storageTypeRaw);

        UserStorageFactory.setType(storageType);
        MessageStorageFactory.setType(storageType);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
