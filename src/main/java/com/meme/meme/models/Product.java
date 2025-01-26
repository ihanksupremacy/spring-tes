package com.meme.meme.models;

import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "product")
public class Product {
    private String id;
    private String image;
    private String product_name;
    private LocalDateTime addTime;
    private String userId; // Menyimpan ID user
    private User user;  // Relasi dengan User
    
    // Getter dan Setter untuk userId
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Setter untuk user
    public void setUser(User user) {
        this.user = user;
    }
}
