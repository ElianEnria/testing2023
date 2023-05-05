package com.testing2023.grupo1.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Task {
    @Id
    private Long id;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private boolean isDone;

    // constructor, getters y setters
    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

}
