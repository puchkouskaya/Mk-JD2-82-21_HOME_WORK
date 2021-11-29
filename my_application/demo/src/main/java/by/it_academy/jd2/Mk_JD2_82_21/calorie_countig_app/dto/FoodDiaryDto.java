package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Recipe;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.api.EEating;

public class FoodDiaryDto {
    private Profile profile;
    private Recipe recipe;
    private Product product;
    private double measure;
    private EEating eating;


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    public EEating getEating() {
        return eating;
    }

    public void setEating(EEating eating) {
        this.eating = eating;
    }
}
