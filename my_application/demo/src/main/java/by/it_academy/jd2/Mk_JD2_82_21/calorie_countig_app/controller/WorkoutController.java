package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.controller;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WorkoutByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.WorkoutDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Workout;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IWorkoutService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.util.ProfileCheck;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("api/profile")
public class WorkoutController {

    private final IWorkoutService workoutService;
    private final IProfileService profileService;
    private final ProfileCheck profileCheck;

    public WorkoutController(IWorkoutService workoutService, IProfileService profileService, ProfileCheck profileCheck) {
        this.workoutService = workoutService;
        this.profileService = profileService;
        this.profileCheck = profileCheck;
    }

    @GetMapping("/{id_profile}/journal/active/")
    public ResponseEntity<?> getActives(@PathVariable("id_Profile") Long idProfile,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                        @RequestParam("dt_start") Long start,
                                        @RequestParam("dt_end") Long end){

        if (profileCheck.checkProfile(idProfile)) {
            try {
                Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
                LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault());
                LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.systemDefault());
                WorkoutByDateDto activeByDate = workoutService
                        .findAllByProfileIdAndDateCreate(startTime, endTime, idProfile, pageable);
                return new ResponseEntity<>(activeByDate, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id_profile}/journal/active/{id_active}")
    public ResponseEntity<Workout> getWorkout(@PathVariable("id_Profile") Long idProfile,
                                              @PathVariable("id_active") Long idWorkout) {
        if (profileCheck.checkProfile(idProfile)) {
            try {
                Workout exercise = workoutService.get(idWorkout);
                return new ResponseEntity<>(exercise, HttpStatus.OK);
            } catch (IllegalArgumentException  ex) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{id_profile}/journal/active")
    public ResponseEntity<?> createActive(@PathVariable("id_Profile") Long idProfile,
                                          @RequestBody WorkoutDto workoutDto){

        if (profileCheck.checkProfile(idProfile)) {
            try {
                workoutDto.setProfile(profileService.get(idProfile));
                workoutService.save(workoutDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (IllegalArgumentException ex) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{id_profile}/journal/active/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<?> updateActive(@PathVariable("id_Profile") Long idProfile,
                                          @PathVariable("id_active") Long idActive,
                                          @PathVariable("dt_update") Long dtUpdate,
                                          @RequestBody WorkoutDto workoutDto) {

        if (profileCheck.checkProfile(idProfile)) {
            try {
                LocalDateTime updateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
                workoutDto.setProfile(profileService.get(idProfile));
                workoutService.update(workoutDto, idActive, updateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (OptimisticLockException ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id_profile}/journal/active/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteActive(@PathVariable("id_Profile") Long idProfile,
                                          @PathVariable("id_active") Long idActive,
                                          @PathVariable("dt_update") Long dtUpdate) {

        if (profileCheck.checkProfile(idProfile)) {
            try {
                LocalDateTime updateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
                workoutService.delete(idActive, updateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (OptimisticLockException ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
