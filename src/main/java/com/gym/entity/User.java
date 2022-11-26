package com.gym.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private double price;

    enum Role {
        CLIENT,
        COACH,
        ADMIN
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return String.valueOf(role);
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
