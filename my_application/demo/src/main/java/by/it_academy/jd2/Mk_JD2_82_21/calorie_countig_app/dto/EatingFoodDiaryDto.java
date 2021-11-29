package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.FoodDiary;

import java.util.List;

public class EatingFoodDiaryDto {
    private List<FoodDiary> EatingFoodDiary;
    private double sumOfCalories;

    public List<FoodDiary> getEatingFoodDiary() {
        return EatingFoodDiary;
    }

    public void setEatingFoodDiary(List<FoodDiary> eatingFoodDiary) {
        EatingFoodDiary = eatingFoodDiary;
    }

    public double getSumOfCalories() {
        return sumOfCalories;
    }

    public void setSumOfCalories(double sumOfCalories) {
        this.sumOfCalories = sumOfCalories;
    }
}
