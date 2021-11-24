package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.IngredientsDish;

import java.time.LocalDateTime;
import java.util.List;

public class RecipeDto {
    private String name;

    private List<IngredientsDish> ingredientsDishes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientsDish> getIngredientsDishes() {
        return ingredientsDishes;
    }

    public void setIngredientsDishes(List<IngredientsDish> ingredientsDishes) {
        this.ingredientsDishes = ingredientsDishes;
    }
}
