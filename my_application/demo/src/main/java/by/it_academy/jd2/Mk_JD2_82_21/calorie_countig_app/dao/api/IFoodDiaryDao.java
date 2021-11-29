package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.FoodDiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IFoodDiaryDao extends JpaRepository<FoodDiary, Long> {
    Page<FoodDiary> findByProfileId(Long id, Pageable pageable);
    List<FoodDiary> findAllByDateCreateBetweenAndProfileId(LocalDateTime start, LocalDateTime end, Long id);
    List<FoodDiary> findAllByProfileId(Long id);

}
