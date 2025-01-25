package com.meme.meme.controllers;

import com.meme.meme.services.tokenService;
import com.meme.meme.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class tokenController {

    @Autowired
    private tokenService tokenService;

    // Endpoint untuk mengekstrak dan memverifikasi token
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        return tokenService.extractToken(token);
    }

    // Endpoint untuk memverifikasi apakah token valid atau tidak
    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestParam("token") String token) {
        boolean isValid = tokenService.validateToken(token);
        
        if (isValid) {
            return ResponseEntity.ok(new Response(true, "Token valid"));
        } else {
            return ResponseEntity.status(401).body(new Response(false, "Token tidak valid atau sudah kadaluarsa"));
        }
    }
}
