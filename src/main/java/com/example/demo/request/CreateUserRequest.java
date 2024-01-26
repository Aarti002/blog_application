package com.example.demo.request;
//import lombok.*;


public class CreateUserRequest {

    private int id;
    private String username;
    private String email;
    

    public CreateUserRequest(String name, String email) {
        this.username = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return username;
    }
    public void setName(String name) {
        this.username = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
