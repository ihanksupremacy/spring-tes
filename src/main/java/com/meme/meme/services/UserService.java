package com.meme.meme.services;

import com.meme.meme.models.User;
import com.meme.meme.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Pastikan Anda memiliki repository untuk User

    @Autowired
private PasswordEncoder passwordEncoder; // Ganti dari BCryptPasswordEncoder

    // Memeriksa apakah username sudah ada
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    // Menyimpan user baru
    public User registerUser(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
     public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
}

