package com.gym.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TransactionFilterDto implements Serializable {
    private LocalDate start;
    private LocalDate end;

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
