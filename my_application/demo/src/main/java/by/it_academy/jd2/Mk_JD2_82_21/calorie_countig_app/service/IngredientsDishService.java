package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api.IIngredientDishDao;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.IngredientsDish;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IIngredientDishService;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IngredientsDishService implements IIngredientDishService {
    private final IIngredientDishDao ingredientDishDao;
    private final IProductService productService;

    public IngredientsDishService (IIngredientDishDao ingredientDishDao, IProductService productService) {
        this.ingredientDishDao = ingredientDishDao;
        this.productService = productService;
    }

    @Override
    public IngredientsDish get(Long id) {
        return this.ingredientDishDao.findById(id)
                .orElseThrow(
                () -> new IllegalArgumentException("Ingredient по ID не найден!")
        );
    }

    @Override
    public IngredientsDish save(IngredientsDish ingredientsDish) {
        Product product = this.productService.get(ingredientsDish.getProduct().getId());
        ingredientsDish.setProduct(product);
        LocalDateTime localDateTime = LocalDateTime.now();
        ingredientsDish.setDateCreate(localDateTime);
        ingredientsDish.setDateUpdate(ingredientsDish.getDateCreate());
        return this.ingredientDishDao.save(ingredientsDish);
    }

    @Override
    public IngredientsDish update(IngredientsDish ingredientsDish, Long id) {
        IngredientsDish updateIngredientsDish = get(id);
        updateIngredientsDish.setProduct(ingredientsDish.getProduct());
        updateIngredientsDish.setMeasure(ingredientsDish.getMeasure());
        return this.ingredientDishDao.save(updateIngredientsDish);
    }

    @Override
    public void delete(Long id, LocalDateTime date) {
        this.ingredientDishDao.deleteById(id);
    }
}
