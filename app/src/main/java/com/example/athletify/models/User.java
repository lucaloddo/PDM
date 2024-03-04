package com.example.athletify.models;

public class User {

    private String email;
    private String id;

    public User() {
    }

    public User(String email, String id) {
        this.email = email;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
