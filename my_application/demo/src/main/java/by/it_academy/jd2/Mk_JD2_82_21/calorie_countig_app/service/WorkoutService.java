package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api.IWorkoutDao;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WorkoutByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WorkoutDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Workout;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IWorkoutService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations.api.IWorkoutByDateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class WorkoutService implements IWorkoutService {

    private final IWorkoutDao workoutDao;
    private final IWorkoutByDateService workoutByDateService;
    private final UserHolder userHolder;

    public WorkoutService(IWorkoutDao workoutDao, IWorkoutByDateService workoutByDateService, UserHolder userHolder) {
        this.workoutDao = workoutDao;
        this.workoutByDateService = workoutByDateService;
        this.userHolder = userHolder;
    }

    @Override
    public Workout save(WorkoutDto workoutDto) {
        Workout workout = new Workout();
        workout.setUser(userHolder.getUser());
        workout.setName(workoutDto.getName());
        workout.setProfile(workoutDto.getProfile());
        workout.setCalories(workoutDto.getCalories());
        LocalDateTime dateCreate = LocalDateTime.now().withNano(0);
        workout.setDateCreate(dateCreate);
        workout.setDateUpdate(dateCreate);
        return workoutDao.save(workout);
    }

    @Override
    public WorkoutByDateDto findAllByProfileIdAndDateCreate(LocalDateTime start, LocalDateTime end,
                                                              Long id, Pageable pageable) {
        Page<Workout> workouts = workoutDao.findAllByDateCreateBetweenAndProfileId(start, end, id, pageable);
            return workoutByDateService.getWorkoutByDate(workouts);
    }

    @Override
    public Workout get(Long id) {
        return workoutDao.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Workout по ID не найден!")
                );
    }

    @Override
    public Workout  update(WorkoutDto workoutDto, Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Workout updatedWorkout = get(id);

        if (updatedWorkout.getDateUpdate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как тренировка была изменена");
        } else {
            updatedWorkout.setName(workoutDto.getName());
            updatedWorkout.setCalories(workoutDto.getCalories());
            updatedWorkout.setProfile(workoutDto.getProfile());
            LocalDateTime dateUpdate = LocalDateTime.now();
            updatedWorkout.setDateUpdate(dateUpdate);
            return workoutDao.save(updatedWorkout);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Workout deletedWorkout = get(id);
        if (deletedWorkout == null) {
            throw new IllegalArgumentException("Workout по ID не найден!");
        }
        if (deletedWorkout.getDateUpdate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Удаление не может быть выполнено, так как тренировка была изменена");
        } else {
            workoutDao.deleteById(id);
        }
    }
}
