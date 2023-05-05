package com.testing2023.grupo1.Controller;

import com.testing2023.grupo1.Entity.User;
import com.testing2023.grupo1.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserTaskDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream().map(UserTaskDto::new).collect(Collectors.toList());
    }

    @PostMapping("/signin")
    public UserTaskDto signin(@RequestBody UserTaskDto userTaskDto) {
        User user = userTaskDto.toUserTask();
        User newUser = userService.createUser(user);
        return new UserTaskDto(newUser);
    }

    @PostMapping("/login")
    public UserTaskDto login(@RequestBody UserTaskDto userTaskDto) {
        User user = userTaskDto.toUserTask();
        User existingUser = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existingUser == null) {
            throw new RuntimeException("Invalid username or password");
        }
        return new UserTaskDto(existingUser);
    }

    @PutMapping("/{id}")
    public UserTaskDto updateUser(@PathVariable Long id, @RequestBody UserTaskDto userTaskDto) {
        User user = userTaskDto.toUserTask();
        User updatedUser = userService.updateUser(id, user);
        return new UserTaskDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    public static class UserTaskDto {
        private Long id;
        private String username;
        private String password;

        public UserTaskDto() {}

        public UserTaskDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public User toUserTask() {
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
    }
}
