package com.meme.meme.services;

import com.meme.meme.models.User;
import com.meme.meme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Pastikan Anda memiliki repository untuk User

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Untuk mengenkripsi password

    // Memeriksa apakah username sudah ada
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    // Menyimpan user baru
    public User registerUser(User user) {
        // Mengenkripsi password sebelum menyimpannya
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Menyimpan user ke dalam database
        return userRepository.save(user);
    }
}
