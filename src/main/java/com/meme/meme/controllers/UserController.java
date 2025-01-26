package com.meme.meme.controllers;

import com.meme.meme.models.Response;
import com.meme.meme.models.User;
import com.meme.meme.services.UserService;
import com.meme.meme.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint untuk registrasi pengguna baru
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role) {

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);

            // Panggil service untuk mendaftar
            userService.registerUser(user);

            // Menyusun respons dengan data pengguna
            User userResponse = new User();
            userResponse.setId(user.getId());
            userResponse.setUsername(user.getUsername());
            userResponse.setRole(user.getRole());
            userResponse.setPassword(null); // Set password ke null agar tidak dikirim

            return ResponseEntity.ok(new Response<>(true, "User berhasil ditambahkan", userResponse));
        } catch (RuntimeException e) {
            // Menangani error jika username sudah ada
            return ResponseEntity.status(400).body(new Response<>(false, e.getMessage(), null));
        }
    }

    // Endpoint untuk login pengguna
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        try {
            // Periksa apakah username valid
            if (loginUser.getUsername() == null || loginUser.getPassword() == null) {
                return ResponseEntity.status(400).body(new Response<>(false, "Username atau password tidak boleh kosong", null));
            }

            User user = userService.getUserByUsername(loginUser.getUsername());

            // Memeriksa apakah user ditemukan dan password sesuai dengan yang di-encode
            if (user == null || !userService.checkPassword(loginUser.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body(new Response<>(false, "Username atau password salah", null));
            }

            // Gunakan username dan role untuk generate token
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

            // Membuat objek Response hanya dengan token
            return ResponseEntity.ok(new Response<>(true, "Login berhasil", token));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(false, "Terjadi kesalahan di server: " + e.getMessage(), null));
        }
    }
}
