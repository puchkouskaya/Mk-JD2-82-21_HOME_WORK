package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.controller;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.FoodDiaryByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.FoodDiaryDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.ProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IFoodDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations.api.ITimeService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.util.ProfileCheck;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/profile")
public class FoodDiaryController {
    private final IFoodDiaryService foodDiaryService;
    private final ProfileService profileService;
    private final ITimeService timeService;
    private final ProfileCheck profileCheck;

    public FoodDiaryController(IFoodDiaryService foodDiaryService, ProfileService profileService, ITimeService timeService, ProfileCheck profileCheck) {
        this.foodDiaryService = foodDiaryService;
        this.profileService = profileService;
        this.timeService = timeService;
        this.profileCheck = profileCheck;
    }

    @GetMapping("/{id_profile}/journal/food")
    public ResponseEntity<Page<FoodDiary>> getFoodDiary(@PathVariable("id_profile") long idProfile,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {

        try {
            if (profileCheck.checkProfile(idProfile)) {
                Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
                Page<FoodDiary> foodDiaries = foodDiaryService.getAllByProfileId(pageable, idProfile);
                return new ResponseEntity<>(foodDiaries, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id_profile}/journal/food/byDay/{day}")
    public ResponseEntity<?> getFoodDiaryByDay(@PathVariable("id_profile") long idProfile,
                                               @PathVariable("day") int day){
        if (profileCheck.checkProfile(idProfile)){
            try {
                LocalDateTime date = timeService.getDate(day);
                LocalDateTime startOfDate = timeService.detStartOfDate(date);
                LocalDateTime endOfDate = timeService.getEndOfDate(startOfDate);

                FoodDiaryByDateDto foodDiaryByDay = foodDiaryService
                        .findAllByProfileIdAndDateCreate(startOfDate, endOfDate, idProfile);
                return new ResponseEntity<>(foodDiaryByDay, HttpStatus.OK);
            } catch (IllegalArgumentException ex){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id_profile}/journal/food/{id_food}")
    public ResponseEntity<?> getFoodDiaryCard(@PathVariable("id_profile") long idProfile,
                                            @PathVariable("id_food") long idFood) {

        if (profileCheck.checkProfile(idProfile)){
            try {
                FoodDiary foodDiary = foodDiaryService.get(idFood);
                return new ResponseEntity<>(foodDiary, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{id_profile}/journal/food")
    public ResponseEntity<?> save (@PathVariable("id_profile") long idProfile,
                                           @RequestBody FoodDiaryDto foodDiaryDto){

        if (profileCheck.checkProfile(idProfile)) {
            try {
                foodDiaryDto.setProfile(profileService.get(idProfile));
                foodDiaryService.save(foodDiaryDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (IllegalArgumentException ex) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(value = "/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> update (@PathVariable("id_profile") long idProfile,
                                           @PathVariable("id_food") Long idFood,
                                           @PathVariable("dt_update") Long dtUpdate,
                                           @RequestBody FoodDiaryDto foodDiaryDto){

        if (profileCheck.checkProfile(idProfile)){
            try {
                LocalDateTime dtUpdateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
                foodDiaryDto.setProfile(profileService.get(idProfile));
                foodDiaryService.update(foodDiaryDto, idFood, dtUpdateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (OptimisticLockException ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(value = "/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> delete (@PathVariable("id_profile") long idProfile,
                                           @PathVariable("id_food") Long id,
                                           @PathVariable("dt_update") Long dtUpdate) {

        if (profileCheck.checkProfile(idProfile)){
            try {
                LocalDateTime dtUpdateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
                foodDiaryService.delete(id, dtUpdateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (OptimisticLockException ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
