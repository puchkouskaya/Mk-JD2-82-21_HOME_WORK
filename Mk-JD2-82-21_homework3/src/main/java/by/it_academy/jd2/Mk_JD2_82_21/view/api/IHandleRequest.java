package by.it_academy.jd2.Mk_JD2_82_21.view.api;

import by.it_academy.jd2.Mk_JD2_82_21.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHandleRequest {
    void addPerson(HttpServletRequest req, HttpServletResponse resp, Person person);
    Person get(HttpServletRequest req);
}
