package com.gym.dto;

import java.io.Serializable;

public class DefaultCategoryDto implements Serializable {
    private int defaultWorkoutCategoryId;
    private int defaultSubscriptionCategoryId;

    public int getDefaultWorkoutCategoryId() {
        return defaultWorkoutCategoryId;
    }

    public void setDefaultWorkoutCategoryId(int defaultWorkoutCategoryId) {
        this.defaultWorkoutCategoryId = defaultWorkoutCategoryId;
    }

    public int getDefaultSubscriptionCategoryId() {
        return defaultSubscriptionCategoryId;
    }

    public void setDefaultSubscriptionCategoryId(int defaultSubscriptionCategoryId) {
        this.defaultSubscriptionCategoryId = defaultSubscriptionCategoryId;
    }
}
