package com.meme.meme.services;

import com.meme.meme.models.Response;
import com.meme.meme.models.User;

import com.meme.meme.util.JwtUtil;
import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class tokenService {

    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private UserService userService; // Menyuntikkan UserService untuk mengambil data pengguna

    // Mengekstrak username dan role dari token
    public ResponseEntity<?> extractToken(String token) {
        try {
            Claims claims = jwtUtils.extractClaims(token);
            String username = claims.getSubject();


            if (username != null ) {
                // Mencari pengguna berdasarkan username untuk mendapatkan data lengkap pengguna
                User user = userService.getUserByUsername(username);

                if (user != null) {
                    // Menyusun respons sesuai dengan format yang diinginkan
                    return ResponseEntity.ok(new Response<>(true, "Token valid", user));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new Response<>(false, "Pengguna tidak ditemukan", null));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new Response<>(false, "Token tidak valid", null));
            }
        } catch (Exception e) {
            e.printStackTrace();  // Menampilkan stack trace untuk debugging
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response<>(false, "Token tidak valid atau kadaluarsa", null));
        }
    }
}
