package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api;

import java.time.LocalDateTime;

public interface IService<T, ID> {
    T get(ID id);
    T save(T item);
    T update(T item, ID id);
    void delete (ID id, LocalDateTime date);
}
