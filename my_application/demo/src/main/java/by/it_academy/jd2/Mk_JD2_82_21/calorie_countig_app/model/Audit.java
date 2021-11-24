package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtCreate;

    @OneToOne
    private User user;

    @Column
    private String description;

    @Column
    private String essenceName;

    @Column
    private Long essenceId;

    public Audit() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEssenceName() {
        return essenceName;
    }

    public void setEssenceName(String essenceName) {
        this.essenceName = essenceName;
    }

    public Long getEssenceId() {
        return essenceId;
    }

    public void setEssenceId(Long essenceId) {
        this.essenceId = essenceId;
    }
}
