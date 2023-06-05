package com.testing2023.grupo1.service;

import com.testing2023.grupo1.controller.requests.user.LoginRequest;
import com.testing2023.grupo1.controller.requests.user.NewUserRequest;
import com.testing2023.grupo1.controller.responses.user.SigninResponse;
import com.testing2023.grupo1.controller.responses.user.TaskResponse;
import com.testing2023.grupo1.controller.responses.user.LoginResponse;
import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.entity.User;
import com.testing2023.grupo1.repository.TaskRepository;
import com.testing2023.grupo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<LoginResponse> getAllUsers() {
        return userRepository.findAll().stream().map(
                user -> new LoginResponse(
                        user.getId(),
                        user.getUsername(),
                        buildTaskResponseList(
                                getUserTasks(user)
                        )

                )
        ).collect(Collectors.toList());
    }

    public SigninResponse createUser(NewUserRequest request) {
        validateUsername(request);
        validatePassword(request.getPassword());

        User user = userRepository.save(
                new User(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return new SigninResponse(
                user.getId(),
                user.getUsername()
        );
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

    private void validateUsername(NewUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ingresado ya está registrado");
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
            throw new RuntimeException("La contraseña debe tener al menos 8 caracteres y contener al menos un número y una letra");
        }
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsernameAndPassword(
                request.getUsername(),
                request.getPassword()
        ).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "El usuario o la contraseña son incorrectas, intente nuevamente."));

        List<Task> tasks = getUserTasks(user);
        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                buildTaskResponseList(tasks)
        );

    }

    private List<TaskResponse> buildTaskResponseList(List<Task> tasks) {
        return tasks.stream().map(
                task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDateTime(),
                        task.getDescription(),
                        task.getIsDone()
                )
        ).collect(Collectors.toList());
    }

    private List<Task> getUserTasks(User user) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return taskRepository.findByUserAndDateTimeAfter(user, currentDateTime);
    }

    public User getUserById(Long loggedUserId) {
        return userRepository.findById(loggedUserId).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Error al encontrar el usuario. Intente nuevamente")
        );
    }
}
