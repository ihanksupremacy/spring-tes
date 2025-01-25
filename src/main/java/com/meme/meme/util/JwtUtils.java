package com.meme.meme.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.meme.meme.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    private static final String secretkey = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @SuppressWarnings("deprecation")
    public String generateToken(User user) {
        Date now = new Date();
        long expirationTime = 1000 * 60 * 60 * 2; // 2 jam
        Date expiryDate = new Date(now.getTime() + expirationTime);
        
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiryDate) // Set expiration
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .signWith(SignatureAlgorithm.HS256, secretkey)
                .compact();
    }


    @SuppressWarnings("deprecation")
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretkey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String extractToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extractToken'");
    }

}
