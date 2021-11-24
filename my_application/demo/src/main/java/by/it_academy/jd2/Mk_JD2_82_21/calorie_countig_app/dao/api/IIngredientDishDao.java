package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.IngredientsDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredientDishDao extends JpaRepository<IngredientsDish, Long> {
}
