package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api.IUserDao;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.LoginDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.api.ERoleUser;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.api.EStatusUser;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class UserService implements IUserService {
    private final IUserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(LoginDto loginDto) {
        User user = new User();
        user.setName(loginDto.getName());
        user.setLogin(loginDto.getLogin());
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setDateCreate(localDateTime);
        user.setDateUpdate(user.getDateCreate());
        user.setRole(ERoleUser.ROLE_ADMIN);
        user.setStatusUser(EStatusUser.ACTIVE);
        return userDao.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }


    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }


//    @Override
//    public Page<User> getAll(Pageable pageable) {
//        return null;
//    }

    @Override
    public User get(Long id) {
        return userDao.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("User по ID не найден!")
                );
    }

//    @Override
//    public User save (User user) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        user.setDateCreate(localDateTime);
//        user.setDateUpdate(user.getDateCreate());
//        user.setRole(ERoleUser.ROLE_USER);
//        user.setStatusUser(EStatusUser.ACTIVE);
//        return userDao.save(user);
//    }

    @Override
    public User update(User user, Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        User updatedUser = get(id);
        if (dtUpdate != updatedUser.getDateUpdate()) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как " +
                    "обновляемый пользователь был изменен");
        } else {
            updatedUser.setName(user.getName());
            updatedUser.setLogin(user.getLogin());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRole(user.getRole());
            updatedUser.setStatusUser(user.getStatusUser());
            updatedUser.setDateCreate(user.getDateCreate());

            LocalDateTime updateDate = LocalDateTime.now();
            updatedUser.setDateUpdate(updateDate);

            return userDao.save(updatedUser);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        User deletedUser = get(id);
        if (dtUpdate != deletedUser.getDateUpdate()) {
            throw new OptimisticLockException("Удадение не может быть выполнено, така как " +
                    "удаляемый пользователь был изменен");
        } else {
            userDao.deleteById(id);
        }
    }
}
