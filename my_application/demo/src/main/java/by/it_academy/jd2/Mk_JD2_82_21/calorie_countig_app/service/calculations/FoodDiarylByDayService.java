package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.FoodDiaryByDateDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.IngredientsDish;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Recipe;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.calculations.api.IFoodDiaryByDayService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodDiarylByDayService implements IFoodDiaryByDayService {
    @Override
    public FoodDiaryByDateDto getFoodDiaryByDay(List<FoodDiary> foodDiaries) {
        FoodDiaryByDateDto foodDiaryByDay = new FoodDiaryByDateDto();

        List<FoodDiary> foodDiaryList = new ArrayList<>();
        double sumOfCalories = 0;
        for (FoodDiary foodDiary : foodDiaries) {
            foodDiaryList.add(foodDiary);
            double measure = foodDiary.getMeasure();
            Recipe recipe = foodDiary.getRecipe();
            Product product = foodDiary.getProduct();

            if (product != null) {
                double productCalories = product.getCalories();
                double productMeasure = product.getMeasure();
                double calories = productCalories * measure / productMeasure;
                sumOfCalories += calories;
            }

            if (recipe != null) {
                double dishMeasure = 0;
                double dishCalories = 0;
                List<IngredientsDish> ingredientsDishList = recipe.getIngredientsDishList();
                for (IngredientsDish ingredientsDish : ingredientsDishList) {
                    Product componentProduct = ingredientsDish.getProduct();
                    double componentProductCalories = componentProduct.getCalories();
                    double componentProductMeasure = componentProduct.getMeasure();
                    Double componentMeasure = ingredientsDish.getMeasure();
                    dishMeasure += componentMeasure;
                    dishCalories += componentProductCalories * componentMeasure / componentProductMeasure;
                    double calories = dishCalories * measure / dishMeasure;
                    sumOfCalories += calories;
                }
            }
        }
        foodDiaryByDay.setFoodDiaries(foodDiaryList);
        foodDiaryByDay.setSumOfCalories(sumOfCalories);

        return foodDiaryByDay;
    }
}
