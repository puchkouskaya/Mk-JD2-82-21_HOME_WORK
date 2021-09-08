package by.it_academy.jd2.Mk_JD2_82_21.model;

import java.io.Serializable;

public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;

    public Post () {

    }

    public Post (long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Post (String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}