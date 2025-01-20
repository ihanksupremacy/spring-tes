package com.meme.meme.controllers;



import com.meme.meme.models.Response;
import com.meme.meme.models.User;
import com.meme.meme.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role) {
        if (userService.usernameExists(username)) {
            return  ResponseEntity.ok(new Response(false,"user sudah ada"));
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        userService.registerUser(user);
        return ResponseEntity.ok(new Response(true,"user sudah ditambahkan"));
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users); // Mengembalikan semua data user dalam format JSON
    }
}
