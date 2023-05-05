package com.testing2023.grupo1.Service;

import com.testing2023.grupo1.Entity.UserTask;
import com.testing2023.grupo1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserTask> getAllUsers() {
        return userRepository.findAll();
    }

    public UserTask getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserTask createUser(UserTask user) {
        UserTask existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("El usuario ingresado ya está registrado");
        }
        return userRepository.save(user);
    }

    public UserTask updateUser(Long userId, UserTask userTaskDetails) {
        UserTask userTask = userRepository.findById(userId).orElse(null);
        if (userTask != null) {
            userTask.setUsername(userTaskDetails.getUsername());
            userTask.setPassword(userTaskDetails.getPassword());
            return userRepository.save(userTask);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long userId) {
        UserTask userTask = userRepository.findById(userId).orElse(null);
        if (userTask != null) {
            userRepository.delete(userTask);
            return true;
        } else {
            return false;
        }
    }
}
