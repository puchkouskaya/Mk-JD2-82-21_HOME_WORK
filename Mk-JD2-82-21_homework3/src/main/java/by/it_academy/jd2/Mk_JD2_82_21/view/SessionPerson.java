package by.it_academy.jd2.Mk_JD2_82_21.view;

import by.it_academy.jd2.Mk_JD2_82_21.model.Person;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.IHandleRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionPerson implements IHandleRequest {
    private final static SessionPerson instance = new SessionPerson ();

    private final static String PERSON_ATTRIBUTE_NAME = "person";

    public void addPerson(HttpServletRequest req, HttpServletResponse resp, Person person) {
        HttpSession session = req.getSession();

        session.setAttribute(PERSON_ATTRIBUTE_NAME, person);
    }

    public Person get(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE_NAME);

        if (person == null) {
            throw new IllegalArgumentException("Вы не сохранили данные в сессию");
        }

        return person;
    }

    public static SessionPerson getInstance() {
        return instance;
    }
}
