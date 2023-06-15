package com.testing2023.grupo1.controller.responses.user;

import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResponse that = (TaskResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(dateTime, that.dateTime) && Objects.equals(description, that.description) && Objects.equals(isDone, that.isDone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, dateTime, description, isDone);
    }
}
