package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Workout;
import org.springframework.data.domain.Page;

public class WorkoutByDateDto {

    private Page<Workout> workouts;
    private double sumOfCalories;

    public Page<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Page<Workout> workouts) {
        this.workouts = workouts;
    }

    public double getSumOfCalories() {
        return sumOfCalories;
    }

    public void setSumOfCalories(double sumOfCalories) {
        this.sumOfCalories = sumOfCalories;
    }
}
