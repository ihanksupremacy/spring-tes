package com.meme.meme.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users") // Koleksi MongoDB bernama "users"
public class User {

    @Id // MongoDB akan otomatis menghasilkan ID
    private String id;

    @Field("username") // Nama field dalam MongoDB (opsional)
    private String username;

    @Field("password")
    private String password;

    @Field("role") // Contoh: "USER", "ADMIN"
    private String role;
}
