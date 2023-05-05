package com.testing2023.grupo1.Controller;

import com.testing2023.grupo1.Entity.UserTask;
import com.testing2023.grupo1.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserTask> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserTask createUser(@RequestBody UserTask userTask) {
        return userService.createUser(userTask);
    }

    @PutMapping("/{id}")
    public UserTask updateUser(@PathVariable Long id, @RequestBody UserTask userTask) {
        return userService.updateUser(id, userTask);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
