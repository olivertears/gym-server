package com.gym.entity;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private Type type;
    private String name;

    enum Type {
        EXPENSE,
        INCOME
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return String.valueOf(type);
    }

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
