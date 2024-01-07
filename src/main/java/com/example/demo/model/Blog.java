package com.example.demo.model;

import jakarta.persistence.*;


@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="title")
    private String title;
    
    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable=false)
    private User user;

    public Blog() {

    }

    public Blog(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user=user;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setUser(User user) {
    	this.user=user;
    }
    public User getUser() {
    	return user;
    }
}
