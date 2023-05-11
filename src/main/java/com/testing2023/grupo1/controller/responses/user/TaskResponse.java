package com.testing2023.grupo1.controller.responses.user;

import com.testing2023.grupo1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String title;
    private LocalDateTime dateTime;
    private String description;
    private Boolean isDone;
}
