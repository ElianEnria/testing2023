package com.testing2023.grupo1.controller.requests.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTaskRequest {
    private String title;
    private LocalDateTime dateTime;
    private String description;
    private Long loggedUserId;
}
