package com.gym.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class WorkoutTimeDto implements Serializable {
    private LocalDate date;
    private int coachId;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }
}
