package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Recipe;
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
public class RecipeAuditService {
    private final IAuditService auditService;
    private final IUserService userService;
    private final UserHolder userHolder;

    public RecipeAuditService(IAuditService auditService, IUserService userService, UserHolder userHolder) {
        this.auditService = auditService;
        this.userService = userService;
        this.userHolder = userHolder;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.RecipeService.save(..))", returning = "result")
    public void methodSaveProduct(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            Recipe recipe = (Recipe) result;

            Audit audit = new Audit();
            audit.setDtCreate(recipe.getDateUpdate());
            audit.setDescription("Создан Recipe " + recipe.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("Recipe");
            audit.setEssenceId(recipe.getId());
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.RecipeService.update(..))", returning = "result")
    public void methodUpdateProduct(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            Recipe recipe = (Recipe) result;

            Audit audit = new Audit();
            audit.setDtCreate(recipe.getDateUpdate());
            audit.setDescription("Изменен Recipe " + recipe.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("Recipe");
            audit.setEssenceId(recipe.getId());
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.RecipeService.delete(..))")
    public void methodDeleteProduct(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];

            Audit audit = new Audit();
            audit.setDtCreate(LocalDateTime.now());
            audit.setDescription("Удален Recipe " + id);
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setEssenceName("Recipe");
            audit.setEssenceId(id);
            auditService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
