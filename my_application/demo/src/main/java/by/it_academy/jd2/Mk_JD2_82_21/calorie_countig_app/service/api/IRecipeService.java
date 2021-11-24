package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.RecipeDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Recipe;

import java.time.LocalDateTime;

public interface IRecipeService {
    Recipe get(Long id);
    Recipe save(RecipeDto recipeDto);
    Recipe update(Recipe recipe, Long id, LocalDateTime date);
    void delete (Long id, LocalDateTime date);
}
