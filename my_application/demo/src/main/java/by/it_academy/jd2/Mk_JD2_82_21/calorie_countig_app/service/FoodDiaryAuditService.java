package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Aspect
@Service
public class FoodDiaryAuditService {
    private final IAuditService auditService;
    private final IUserService userService;
    private final UserHolder userHolder;

    public FoodDiaryAuditService(IAuditService auditService, IUserService userService, UserHolder userHolder) {
        this.auditService = auditService;
        this.userService = userService;
        this.userHolder = userHolder;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.FoodDiaryService.save(..))", returning = "result")
    public void methodSaveProduct(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            FoodDiary foodDiary = (FoodDiary) result;

            Audit audit = new Audit();
            audit.setDtCreate(foodDiary.getDateUpdate());
            audit.setDescription("Создан FoodDiary " + foodDiary.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("FoodDiary");
            audit.setEssenceId(foodDiary.getId());
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.FoodDiaryService.update(..))", returning = "result")
    public void methodUpdateProduct(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            FoodDiary foodDiary = (FoodDiary) result;

            Audit audit = new Audit();
            audit.setDtCreate(foodDiary.getDateUpdate());
            audit.setDescription("Изменен FoodDiary " + foodDiary.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("FoodDiary");
            audit.setEssenceId(foodDiary.getId());
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.FoodDiaryService.delete(..))")
    public void methodDeleteProduct(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];

            Audit audit = new Audit();
            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Удален FoodDiary " + id);
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("FoodDiary");
            audit.setEssenceId(id);
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
