package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.api.EActivityUser;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.api.EGenderUser;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.api.EGoalUser;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "height_user")
    private int height;

    @Column(name = "weight_user")
    private double weight;

    @Column(name = "desired_weight_user")
    private double desiredWeight;

    @Column(name = "date_of_birth_user")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDateTime dateOfBirth;

    @Column(name = "gender_user")
    private EGenderUser gender;

    @Column(name = "activity_user")
    private EActivityUser activity;

    @Column(name = "goal_user")
    private EGoalUser goal;

    @OneToOne
    private User user;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreate;

    @Column
    @Version
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateUpdate;

    public Profile() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDesiredWeight() {
        return desiredWeight;
    }

    public void setDesiredWeight(double desiredWeight) {
        this.desiredWeight = desiredWeight;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public EGenderUser getGender() {
        return gender;
    }

    public void setGender(EGenderUser gender) {
        this.gender = gender;
    }

    public EActivityUser getActivity() {
        return activity;
    }

    public void setActivity(EActivityUser activity) {
        this.activity = activity;
    }

    public EGoalUser getGoal() {
        return goal;
    }

    public void setGoal(EGoalUser goal) {
        this.goal = goal;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}

