package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WorkoutByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Workout;
import org.springframework.data.domain.Page;

public interface IWorkoutByDateService {
    WorkoutByDateDto getWorkoutByDate(Page<Workout> workouts);
}
