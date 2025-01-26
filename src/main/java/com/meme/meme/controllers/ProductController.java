package com.meme.meme.controllers;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.meme.meme.models.Product;
import com.meme.meme.models.User;
import com.meme.meme.repository.ProductRepository;
import com.meme.meme.repository.UserRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        try {
            // Ambil userId dari SecurityContext
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userId = null;
            if (principal instanceof UserDetails) {
                userId = ((UserDetails) principal).getUsername(); // Mengambil username sebagai userId
            } else {
                throw new RuntimeException("User not authenticated properly");
            }

            // Buat userId final untuk digunakan dalam lambda expression
            final String finalUserId = userId;

            // Cari User dengan userId yang diperoleh dari token
            User user = userRepository.findById(finalUserId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + finalUserId));

            // Set properti produk
            product.setUser(user);
            product.setAddTime(LocalDateTime.now());
            product.setUserId(finalUserId); // Pastikan userId diset dengan benar

            // Simpan produk ke database
            productRepository.save(product);
            return "Product added successfully!";

        } catch (Exception e) {
            // Log exception untuk membantu debugging
            e.printStackTrace();
            return "Error while adding product: " + e.getMessage();
        }
    }
}
