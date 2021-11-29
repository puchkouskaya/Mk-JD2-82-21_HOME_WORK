package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WorkoutByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WorkoutDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Workout;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IWorkoutService {
    Workout save(WorkoutDto workoutDto);
    WorkoutByDateDto findAllByProfileIdAndDateCreate(LocalDateTime start, LocalDateTime end, Long id, Pageable pageable);
    Workout get(Long id);
    Workout update(WorkoutDto workoutDto, Long id, LocalDateTime dtUpdate);
    void delete (Long id, LocalDateTime dtUpdate);
}
