package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api.IRecipeDao;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.RecipeDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Recipe;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.IngredientsDish;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IRecipeService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IIngredientDishService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService implements IRecipeService {
    private final IRecipeDao recipeDao;
    private final IIngredientDishService ingredientDishService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public RecipeService(IRecipeDao recipeDao, IIngredientDishService ingredientDishService, UserHolder userHolder, IUserService userService) {
        this.recipeDao = recipeDao;
        this.ingredientDishService = ingredientDishService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    public Recipe get(Long id) {
        return recipeDao.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Recipe по ID не найден!")
                );
    }

    @Override
    public Recipe save(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeDto.getName());
        recipe.setUser(userHolder.getUser());
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        recipe.setDateCreate(localDateTime);
        recipe.setDateUpdate(recipe.getDateCreate());
        List<IngredientsDish> ingredientsDish = recipeDto.getIngredientsDishes();
        for (IngredientsDish ingredient : ingredientsDish) {
            ingredient.setDateCreate(recipe.getDateCreate());
            ingredient.setDateUpdate(ingredient.getDateCreate());
            ingredientDishService.save(ingredient);
        }
        recipe.setIngredientsDishList(ingredientsDish);
        return this.recipeDao.save(recipe);
    }

    @Override
    public Recipe update(Recipe recipe, Long id, LocalDateTime date) {
        Recipe recipe1 = get(id);
        recipe1.setName(recipe.getName());
        List<IngredientsDish> ingredientsDishes = recipe.getIngredientsDishList();
        for (IngredientsDish ingredient : ingredientsDishes) {
            ingredient.setDateCreate(recipe.getDateCreate());
            ingredient.setDateUpdate(recipe.getDateUpdate());
            ingredientDishService.save(ingredient);
        }
        recipe1.setDateUpdate(LocalDateTime.now());
        return this.recipeDao.save(recipe1);
    }

    @Override
    public void delete(Long id, LocalDateTime date) {
        Recipe recipe = get(id);
        if (recipe == null) {
            throw new IllegalArgumentException("Product по ID не найден!");
        }
        this.recipeDao.deleteById(id);
    }

}
