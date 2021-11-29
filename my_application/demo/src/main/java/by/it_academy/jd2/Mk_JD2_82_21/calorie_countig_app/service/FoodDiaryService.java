package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api.IFoodDiaryDao;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.FoodDiaryByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.FoodDiaryDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IFoodDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations.api.IFoodDiaryByDayService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class FoodDiaryService implements IFoodDiaryService {
    private final IFoodDiaryDao foodDiaryDao;
    private final IFoodDiaryByDayService foodDiaryByDayService;

    public FoodDiaryService(IFoodDiaryDao foodDiaryDao, IFoodDiaryByDayService foodDiaryByDayService) {
        this.foodDiaryDao = foodDiaryDao;
        this.foodDiaryByDayService = foodDiaryByDayService;
    }

    @Override
    public FoodDiary get(Long id) {
        return this.foodDiaryDao.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("FoodDiary по ID не найден!")
                );
    }

    @Override
    public FoodDiary save(FoodDiaryDto foodDiaryDto) {
        FoodDiary foodDiary = new FoodDiary();
        foodDiary.setProduct(foodDiaryDto.getProduct());
        foodDiary.setRecipe(foodDiaryDto.getRecipe());
        foodDiary.setEating(foodDiaryDto.getEating());
        foodDiary.setMeasure(foodDiaryDto.getMeasure());
        foodDiary.setProfile(foodDiaryDto.getProfile());
        LocalDateTime dateCreate = LocalDateTime.now().withNano(0);
        foodDiary.setDateCreate(dateCreate);
        foodDiary.setDateUpdate(foodDiary.getDateCreate());
        return this.foodDiaryDao.save(foodDiary);
    }


    @Override
    public FoodDiary update(FoodDiaryDto foodDiaryDto, Long id, LocalDateTime dateUpdate) throws OptimisticLockException {
        FoodDiary foodDiary1 = get(id);
        if (foodDiary1.getDateUpdate().isEqual(dateUpdate)) {
            throw new OptimisticLockException("Невозможно выполнить обновление, так как дневник приема пищи был изменен");
        } else {
            foodDiary1.setProduct(foodDiaryDto.getProduct());
            foodDiary1.setRecipe(foodDiaryDto.getRecipe());
            foodDiary1.setEating(foodDiaryDto.getEating());
            foodDiary1.setMeasure(foodDiaryDto.getMeasure());
            foodDiary1.setProfile(foodDiaryDto.getProfile());

            LocalDateTime updateDate = LocalDateTime.now();
            foodDiary1.setDateUpdate(updateDate);

            return foodDiaryDao.save(foodDiary1);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dateUpdate)  throws OptimisticLockException {
        FoodDiary deletedFoodDiary = get(id);
        if (deletedFoodDiary == null) {
            throw new IllegalArgumentException("дневник приема пищи по ID не найден!");
        }
        if (deletedFoodDiary.getDateUpdate().isEqual(dateUpdate)) {
            throw new OptimisticLockException("Невозможно выполнить удаление, так как дневник приема пищи был изменен");
        } else {
            foodDiaryDao.deleteById(id);
        }
    }

    @Override
    public Page<FoodDiary> getAll(Pageable pageable) {
        return foodDiaryDao.findAll(pageable);
    }


    @Override
    public Page<FoodDiary> getAllByProfileId(Pageable pageable, Long profileId) {
        return foodDiaryDao.findByProfileId(profileId, pageable);
    }

    @Override
    public FoodDiaryByDateDto findAllByProfileIdAndDateCreate(LocalDateTime start, LocalDateTime end, Long id) {
        List<FoodDiary> foodDiaryList = foodDiaryDao.findAllByDateCreateBetweenAndProfileId(start, end, id);
        return foodDiaryByDayService.getFoodDiaryByDay(foodDiaryList);
    }

    @Override
    public List<FoodDiary> findAllByProfile(long id) {
        return foodDiaryDao.findAllByProfileId(id);
    }

}
