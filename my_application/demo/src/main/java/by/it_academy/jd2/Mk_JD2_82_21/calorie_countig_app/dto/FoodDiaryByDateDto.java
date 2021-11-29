package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.FoodDiary;

import java.util.List;

public class FoodDiaryByDateDto {
    private List<FoodDiary> foodDiaries;
    private double sumOfCalories;

    public List<FoodDiary> getFoodDiaries() {
        return foodDiaries;
    }

    public void setFoodDiaries(List<FoodDiary> foodDiaries) {
        this.foodDiaries = foodDiaries;
    }

    public double getSumOfCalories() {
        return sumOfCalories;
    }

    public void setSumOfCalories(double sumOfCalories) {
        this.sumOfCalories = sumOfCalories;
    }
}
