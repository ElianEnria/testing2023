package com.testing2023.grupo1.Controller;

import com.testing2023.grupo1.Entity.UserTask;
import com.testing2023.grupo1.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/register")
    public ResponseEntity<UserTask> register(@RequestBody UserTask user) {
        UserTask newUser = userService.createUser(user);
        return ResponseEntity.created(URI.create("/users/" + newUser.getId())).body(newUser);
    }

}
