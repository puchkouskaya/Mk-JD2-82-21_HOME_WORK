package by.it_academy.jd2.Mk_JD2_82_21.view;

import by.it_academy.jd2.Mk_JD2_82_21.model.Person;
import by.it_academy.jd2.Mk_JD2_82_21.view.api.IHandleRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CookiePerson implements IHandleRequest {
    private static final CookiePerson instanse = new CookiePerson ();

    private static final String FIRST_NAME_COOKIES_NAME = "firstName";
    private static final String LAST_NAME_COOKIES_NAME = "lastName";
    private static final String AGE_COOKIES_NAME = "age";

    private CookiePerson() {

    }

    public void addPerson(HttpServletRequest req, HttpServletResponse resp, Person person) {
        saveCookies(resp, FIRST_NAME_COOKIES_NAME, person.getFirstName());
        saveCookies(resp, LAST_NAME_COOKIES_NAME, person.getLastName());
        saveCookies(resp, AGE_COOKIES_NAME, String.valueOf(person.getAge()));
    }
    public Person get(HttpServletRequest req) {
        Person person = new Person();
        person.setFirstName(getValueFromCookiePerson (req, FIRST_NAME_COOKIES_NAME));
        person.setLastName(getValueFromCookiePerson (req, LAST_NAME_COOKIES_NAME));
        person.setAge(Integer.parseInt(getValueFromCookiePerson (req, AGE_COOKIES_NAME)));
        return person;
    }

    public static String getValueFromCookiePerson(HttpServletRequest req, String key){
        String val = req.getParameter(key);

        if (val == null) {
            Cookie[] cookies = req.getCookies();

            if(cookies != null) {
                val = Arrays.stream(cookies)
                        .filter(c -> key.equalsIgnoreCase(c.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);
            }
        }
        if (val == null) {
            throw new IllegalArgumentException("Не передан один из обязательных параметров");
        }
        return val;
    }

    public static void saveCookies(HttpServletResponse resp, String key, String val) {
        Cookie myCookie = new Cookie(key, URLEncoder.encode(val, StandardCharsets.UTF_8));
        myCookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
        resp.addCookie(myCookie);
    }

    public static CookiePerson getInstanse() {
        return instanse;
    }
}
