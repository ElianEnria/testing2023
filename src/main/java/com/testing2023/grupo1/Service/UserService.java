package com.testing2023.grupo1.Service;

import com.testing2023.grupo1.Entity.Task;
import com.testing2023.grupo1.Entity.User;
import com.testing2023.grupo1.Repository.TaskRepository;
import com.testing2023.grupo1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }
    // rest of the code...


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
//        userRepository.findByUsername(user.getUsername())
//                .ifPresent(existingUser -> {
//                    throw new RuntimeException("El usuario ingresado ya está registrado");
//                });
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("El usuario ingresado ya está registrado");
        }

        String password = user.getPassword();
        if (password == null || password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
            throw new RuntimeException("La contraseña debe tener al menos 8 caracteres y contener al menos un número y una letra");
        }

        return userRepository.save(user);
    }

    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }
    //nuevo 06/05/2023 revisar ultima
    public List<Task> getUserTasksByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password).orElse(null);
        if (user != null) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());//verifica la hora de la zona
            return taskRepository.findByUserAndDateTimeAfter(user, currentDateTime);
        } else {
            throw  new RuntimeException("El usuario o la contraseña ingresada es incorrecta");
        }
    }


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("El usuario o la contraseña ingresada es incorrecta");

        }
    }
}
