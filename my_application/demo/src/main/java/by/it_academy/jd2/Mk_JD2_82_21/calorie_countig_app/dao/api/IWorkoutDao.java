package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IWorkoutDao extends JpaRepository<Workout, Long> {
    Page<Workout> findAllByDateCreateBetweenAndProfileId(LocalDateTime start, LocalDateTime end,
                                                           Long id, Pageable pageable);
}
