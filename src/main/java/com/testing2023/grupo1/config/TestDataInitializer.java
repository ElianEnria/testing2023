package com.testing2023.grupo1.config;

import com.testing2023.grupo1.Entity.Task;
import com.testing2023.grupo1.Entity.UserTask;
import com.testing2023.grupo1.Repository.TaskRepository;
import com.testing2023.grupo1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class TestDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TestDataInitializer(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UserTask userTask = new UserTask();
        userTask.setId(1L);
        userTask.setUsername("usuario");
        userTask.setPassword("contraseña");
        userRepository.save(userTask);

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Tarea 1");
        task1.setDate(LocalDate.now());
        task1.setTime(LocalTime.now());
        task1.setDescription("Descripción de la tarea 1");
        task1.setIsDone(false);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Tarea 2");
        task2.setDate(LocalDate.now());
        task2.setTime(LocalTime.now());
        task2.setDescription("Descripción de la tarea 2");
        task2.setIsDone(true);
        taskRepository.save(task2);
    }
}
