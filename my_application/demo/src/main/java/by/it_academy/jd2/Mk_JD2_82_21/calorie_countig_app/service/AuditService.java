package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api.IAuditDao;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IAuditService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService implements IAuditService {
    private final IAuditDao auditDao;

    public AuditService(IAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    @Override
    public Audit get(Long id) {
        return auditDao.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Audit по id не найден")
                );
    }

    @Override
    public Audit save(Audit audit) {
        return auditDao.save(audit);
    }

    @Override
    public Audit update(Audit audit, Long id) {
        return null;
    }

    @Override
    public void delete(Long id, LocalDateTime date) {
        auditDao.deleteById(id);
    }
}
