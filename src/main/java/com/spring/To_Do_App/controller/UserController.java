package com.spring.To_Do_App.controller;

import com.spring.To_Do_App.model.User;
import com.spring.To_Do_App.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Constructor injection of the UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET endpoint to retrieve a user by their ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    // PUT endpoint to update user details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
}