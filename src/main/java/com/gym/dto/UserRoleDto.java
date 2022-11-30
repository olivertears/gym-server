package com.gym.dto;

import java.io.Serializable;

public class UserRoleDto implements Serializable {
    private int id;
    private Role role;
    private double price;

    enum Role {
        CLIENT,
        COACH,
        ADMIN
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return String.valueOf(role);
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

}
