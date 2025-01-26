package com.meme.meme.util;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "mySecretKeyForJwtToken12345678901234567890"; 
    private static final long EXPIRATION_TIME = 3600000;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Membuat Token
    public String generateToken(String username, String role) {
        return Jwts.builder()
            .subject(username) // username
            .claim("role", role)  // Menambahkan klaim role
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key)
            .compact();
    }

    // Ekstrak Claims
    
public Claims extractClaims(String token) {
    return Jwts.parser()
               .verifyWith(key)  
               .build()
               .parseSignedClaims(token)  
               .getPayload(); 
}



   
}
