package com.testing2023.grupo1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
//    private LocalDate date;
//    private LocalTime time;
    private LocalDateTime dateTime;
    private String description;
    private Boolean isDone;
    @ManyToOne
    private User user;

    // constructor, getters y setters
}
