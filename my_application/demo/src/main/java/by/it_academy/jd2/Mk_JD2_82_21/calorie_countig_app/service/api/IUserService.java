package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.LoginDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;


public interface IUserService {
    User get(Long id);
    User save (LoginDto loginDto);
    User update(User user, Long id, LocalDateTime dtUpdate);
    void delete (Long id, LocalDateTime dtUpdate);
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
//    Page<User> getAll (Pageable pageable);
}
