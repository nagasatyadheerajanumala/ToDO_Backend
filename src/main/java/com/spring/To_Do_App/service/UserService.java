package com.spring.To_Do_App.service;

import com.spring.To_Do_App.model.User;
import com.spring.To_Do_App.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user.
     * In production, you should hash the password and perform validations.
     */
    public User registerUser(User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        // Optionally, hash the password here before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    /**
     * Finds a user by username.
     */
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    /**
     * Retrieves a user by ID.
     */
    public User getUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUser(Long id, User userDetails) {
        User existingUser = getUser(id);
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setUsername(userDetails.getUsername());
        // Update other fields as needed (and hash password if updated)
        return userRepo.save(existingUser);
    }


}