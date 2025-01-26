package com.meme.meme.controllers;

import com.meme.meme.services.tokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class tokenController {

    @Autowired
    private tokenService tokenService;

    // Endpoint untuk mengekstrak dan memverifikasi token
    @PostMapping("/extract")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        return tokenService.extractToken(token);
    }

   
}
