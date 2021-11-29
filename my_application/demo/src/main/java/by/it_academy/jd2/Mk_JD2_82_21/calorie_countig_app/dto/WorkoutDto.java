package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto;


import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Profile;

public class WorkoutDto {

    private String name;
    private double calories;
    private Profile profile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
