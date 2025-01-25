package com.meme.meme.services;

import com.meme.meme.models.Response;
import com.meme.meme.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class tokenService {

    @Autowired
    private JwtUtils jwtUtils;

    // Mengekstrak username dari token
    public ResponseEntity<?> extractToken(String token) {
        try {
            // Mengekstrak klaim dari token
            String username = jwtUtils.extractToken(token);
            
            if (username != null) {
                return ResponseEntity.ok(new Response(true, "Token valid. Username: " + username));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(false, "Token tidak valid"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(false, "Token tidak valid atau kadaluarsa"));
        }
    }

    // Memverifikasi apakah token valid
    public boolean validateToken(String token) {
        try {
            // Mengambil klaim dari token untuk memverifikasi token
            Claims claims = jwtUtils.extractAllClaims(token);
            // Jika klaim bisa diekstraksi dengan sukses, maka token valid
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
