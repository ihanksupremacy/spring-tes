package com.meme.meme.models;

public class LoginResponse {
    private String token;
    private User user;

    // Constructor
    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    // Getter dan Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
