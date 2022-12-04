package com.gym.entity;

import java.io.Serializable;

public class ChartValue implements Serializable {
    private String key;
    private Double value;

    public ChartValue(String key, Double value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}