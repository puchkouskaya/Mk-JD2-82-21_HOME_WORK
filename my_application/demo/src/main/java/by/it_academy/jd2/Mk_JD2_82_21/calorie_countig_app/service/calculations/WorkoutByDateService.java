package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WorkoutByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Workout;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations.api.IWorkoutByDateService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class WorkoutByDateService implements IWorkoutByDateService {
    @Override
    public WorkoutByDateDto getWorkoutByDate(Page<Workout> workouts) {
        WorkoutByDateDto workoutByDateDto = new WorkoutByDateDto();
        double sumOfCalories = 0;
        for (Workout workout : workouts) {
            sumOfCalories += workout.getCalories();
        }

        workoutByDateDto.setWorkouts(workouts);
        workoutByDateDto.setSumOfCalories(sumOfCalories);
        return  workoutByDateDto;
    }
}
