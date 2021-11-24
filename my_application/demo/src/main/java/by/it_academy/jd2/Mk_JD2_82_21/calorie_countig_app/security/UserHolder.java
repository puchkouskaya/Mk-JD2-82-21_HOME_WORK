package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.security;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {
    private final IUserService userService;

    public UserHolder (IUserService userService) {
        this.userService = userService;
    }
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public User getUser(){
        String login = getAuthentication().getName();
        return userService.findByLogin(login);
    }
}
