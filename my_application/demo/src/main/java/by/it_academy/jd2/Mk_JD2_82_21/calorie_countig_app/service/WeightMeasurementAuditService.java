package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.WeightMeasurement;
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
public class WeightMeasurementAuditService {
    private final IAuditService auditService;
    private final IUserService userService;
    private final UserHolder userHolder;

    public WeightMeasurementAuditService(IAuditService auditService, IUserService userService, UserHolder userHolder) {
        this.auditService = auditService;
        this.userService = userService;
        this.userHolder = userHolder;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.WeightMeasurementService.save(..))", returning = "result")
    public void methodSaveProduct(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            WeightMeasurement weightMeasurement = (WeightMeasurement) result;

            Audit audit = new Audit();
            audit.setDtCreate(weightMeasurement.getDateUpdate());
            audit.setDescription("Создан WeightMeasurement " + weightMeasurement.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("WeightMeasurement");
            audit.setEssenceId(weightMeasurement.getId());
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.WeightMeasurementService.update(..))", returning = "result")
    public void methodUpdateProduct(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            WeightMeasurement weightMeasurement = (WeightMeasurement) result;

            Audit audit = new Audit();
            audit.setDtCreate(weightMeasurement.getDateUpdate());
            audit.setDescription("Изменен WeightMeasurement " + weightMeasurement.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("WeightMeasurement");
            audit.setEssenceId(weightMeasurement.getId());
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.WeightMeasurementService.delete(..))")
    public void methodDeleteProduct(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];

            Audit audit = new Audit();
            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Удален WeightMeasurement " + id);
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("WeightMeasurement");
            audit.setEssenceId(id);
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
