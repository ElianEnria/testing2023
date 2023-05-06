package com.testing2023.grupo1.Service;

import com.testing2023.grupo1.Entity.Task;
import com.testing2023.grupo1.Entity.User;
import com.testing2023.grupo1.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public Task create(Task task) {
//        LocalDate currentDate = LocalDate.now();
//        LocalTime currentTime = LocalTime.now();
//
//        if (task.getDate().isBefore(currentDate)) {
//            throw new RuntimeException("La fecha de la tarea debe ser posterior a la fecha actual");
//        } else if (task.getDate().equals(currentDate) && task.getTime().isBefore(currentTime)) {
//            throw new RuntimeException("La hora de la tarea debe ser posterior a la hora actual");
//        }
        /*
        * verificamos si la fecha de la tarea es anterior a la fecha actual. Si es así, lanzamos una excepción.
        * Si la fecha de la tarea es posterior o igual a la fecha actual, verificamos si la hora de la tarea es posterior a la hora actual.
        * Si no es así, lanzamos otra excepción. S
        * i la fecha y hora de la tarea son posteriores a la fecha y hora actual, guardamos la tarea en el repositorio y la devolvemos.
        * */

        //modificaion nueva 06
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        if (task.getDate().isBefore(currentDate)) {
            throw new RuntimeException("La fecha de la tarea debe ser posterior a la fecha actual");
        } else if (task.getDate().equals(currentDate) && task.getTime().isBefore(currentTime)) {
            throw new RuntimeException("La hora de la tarea debe ser posterior a la hora actual");
        } else if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new RuntimeException("El título de la tarea es obligatorio");
        } else if (task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new RuntimeException("La descripción de la tarea es obligatoria");
        }

        return taskRepository.save(task);
    }


    public List<Task> getTasksByUser(User user) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return taskRepository.findByUserAndDateTimeAfter(user, currentDateTime);
    }



    public Task updateTask(Long taskId, Task taskDetails) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setTitle(taskDetails.getTitle());
            task.setDate(taskDetails.getDate());
            task.setTime(taskDetails.getTime());
            task.setDescription(taskDetails.getDescription());
            task.setIsDone(taskDetails.getIsDone());
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public boolean deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            taskRepository.delete(task);
            return true;
        } else {
            return false;
        }
    }
}
