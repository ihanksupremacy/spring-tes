package com.meme.meme.services;

import com.meme.meme.models.User;
import com.meme.meme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method untuk mendaftarkan user baru
    public void registerUser(User user) {
        // Cek apakah username sudah ada
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username sudah digunakan");
        }

        // Enkode password sebelum disimpan
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Simpan user baru ke database
        userRepository.save(user);
    }

    // Mengambil semua user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Mengambil user berdasarkan username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan dengan username: " + username));
    }

    // Mengecek apakah password yang diberikan cocok dengan password yang ter-encrypt
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
