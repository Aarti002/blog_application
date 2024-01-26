package com.example.demo.request;
//import lombok.*;


public class EditUserDetailsRequest {

    private int id;
    private String username;
    private String email;
    

    public EditUserDetailsRequest(String name, String email) {
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
