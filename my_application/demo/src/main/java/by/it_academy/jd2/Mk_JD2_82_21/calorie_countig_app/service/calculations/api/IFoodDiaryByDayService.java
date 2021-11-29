package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.FoodDiaryByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.FoodDiary;

import java.util.List;

public interface IFoodDiaryByDayService {
    FoodDiaryByDateDto getFoodDiaryByDay(List<FoodDiary> foodDiaries);
}
