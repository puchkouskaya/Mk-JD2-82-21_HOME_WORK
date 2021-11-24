package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
