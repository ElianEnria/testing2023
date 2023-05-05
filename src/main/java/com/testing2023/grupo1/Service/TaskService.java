package com.testing2023.grupo1.Service;

import com.testing2023.grupo1.Entity.Task;
import com.testing2023.grupo1.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Task createTask(Task task) {
        return taskRepository.save(task);
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

