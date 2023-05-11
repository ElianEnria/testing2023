package com.testing2023.grupo1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime dateTime;
    private String description;
    private Boolean isDone = false;
    @ManyToOne
    private User user;

    public Task(String title, LocalDateTime dateTime, String description, User user) {
        this.title = title;
        this.dateTime = dateTime;
        this.description = description;
        this.user = user;
    }
}
