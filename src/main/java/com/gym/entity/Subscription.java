package com.gym.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Subscription implements Serializable {
    private int id;
    private int clientId;
    private Type type;
    private double price;
    private LocalDateTime start;
    private LocalDateTime end;

    enum Type {
        STANDARD,
        PREMIUM
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getType() {
        return String.valueOf(type);
    }

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
