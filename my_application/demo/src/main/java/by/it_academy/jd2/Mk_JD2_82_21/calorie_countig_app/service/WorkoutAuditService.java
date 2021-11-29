package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Workout;
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
public class WorkoutAuditService {
    private final IAuditService auditService;
    private final IUserService userService;
    private final UserHolder userHolder;

    public WorkoutAuditService(IAuditService auditService, IUserService userService, UserHolder userHolder) {
        this.auditService = auditService;
        this.userService = userService;
        this.userHolder = userHolder;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.WorkoutService.save(..))", returning = "result")
    public void methodSaveProduct(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            Workout workout = (Workout) result;

            Audit audit = new Audit();
            audit.setDtCreate(workout.getDateUpdate());
            audit.setDescription("Создан Workout " + workout.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("Workout");
            audit.setEssenceId(workout.getId());
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.WorkoutService.update(..))", returning = "result")
    public void methodUpdateProduct(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            Workout workout = (Workout) result;

            Audit audit = new Audit();
            audit.setDtCreate(workout.getDateUpdate());
            audit.setDescription("Изменен Workout " + workout.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("Workout");
            audit.setEssenceId(workout.getId());
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.WorkoutService.delete(..))")
    public void methodDeleteProduct(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];

            Audit audit = new Audit();
            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Удален Workout " + id);
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("Workout");
            audit.setEssenceId(id);
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
