package by.it_academy.jd2.Mk_JD2_82_21.controller;

import by.it_academy.jd2.Mk_JD2_82_21.view.CookiePerson;
import by.it_academy.jd2.Mk_JD2_82_21.view.SessionPerson;
import by.it_academy.jd2.Mk_JD2_82_21.model.Person;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.IHandleRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PersonServlet", urlPatterns = "/person")
public class PersonServlet extends HttpServlet {
    private static final String FIRST_NAME_PARAM_NAME = "firstName";
    private static final String LAST_NAME_PARAM_NAME = "lastName";
    private static final String AGE_PARAM_NAME = "age";
    private static final String HEADER_PARAM_NAME = "selectStorage";
    private static final String STORAGE_TYPE_SESSION_PARAM_NAME = "session";
    private static final String STORAGE_TYPE_COOKIE_PARAM_NAME = "cookie";

    public PersonServlet() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        Person person = new Person();

        person.setFirstName(req.getParameter(FIRST_NAME_PARAM_NAME));
        person.setLastName(req.getParameter(LAST_NAME_PARAM_NAME));
        person.setAge(Integer.parseInt(req.getParameter(AGE_PARAM_NAME)));

        if (person.getFirstName() == null ||
                person.getLastName() == null ||
                person.getAge() == 0) {
            throw new IllegalArgumentException("Не передан один из обязательных параметров");
        }
        String selectHeader = req.getHeader(HEADER_PARAM_NAME);
        IHandleRequest handler = null;

        if (selectHeader.equals(STORAGE_TYPE_COOKIE_PARAM_NAME)) {
            handler = CookiePerson.getInstanse ();
        }
        if (selectHeader.equals(STORAGE_TYPE_SESSION_PARAM_NAME)) {
            handler = SessionPerson.getInstance ();
        }
        if (handler == null){
            throw new IllegalArgumentException("Вы не передали тип хранилища");
        }
        handler.addPerson (req, resp, person);
        writer.write( "Person : " + person.getFirstName() + " " + person.getLastName() + " " + person.getAge());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = res.getWriter();
        String selectHeader  = req.getHeader(HEADER_PARAM_NAME);

        IHandleRequest handler = null;

        if (selectHeader.equals(STORAGE_TYPE_COOKIE_PARAM_NAME)) {
            handler = CookiePerson.getInstanse ();
        }
        if (selectHeader.equals(STORAGE_TYPE_SESSION_PARAM_NAME)) {
            handler = SessionPerson.getInstance ();
        }
        if (handler == null) {
            throw new IllegalArgumentException("Вы не передали тип хранилища");
        }

        Person person = handler.get(req);

        writer.write("FirstName: " + person.getFirstName());
        writer.write("LastName: " + person.getLastName());
        writer.write("Age: " + person.getAge());
    }
}

