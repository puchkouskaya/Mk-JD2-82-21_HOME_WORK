package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.FoodDiaryByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.FoodDiaryDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.FoodDiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IFoodDiaryService{
    FoodDiary save(FoodDiaryDto foodDiaryDto);
    FoodDiary get(Long id);
    FoodDiary update(FoodDiaryDto foodDiaryDto, Long id, LocalDateTime dateUpdate);
    void delete (Long id, LocalDateTime dtUpdate);
    Page<FoodDiary> getAll (Pageable pageable);
    Page<FoodDiary> getAllByProfileId(Pageable pageable, Long profileId);
    FoodDiaryByDateDto findAllByProfileIdAndDateCreate(LocalDateTime start, LocalDateTime end, Long id);
    List<FoodDiary> findAllByProfile(long id);
}



