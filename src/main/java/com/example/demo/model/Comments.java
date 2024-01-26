package com.example.demo.model;
//import lombok.*;
import jakarta.persistence.*;


@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id",nullable=false)
    private User user;
    
    @Column(name="comment")
    private String comment;
    
    @ManyToOne
    @JoinColumn(name = "blog_id",nullable=false)
    private Blog blog;
    
    public Comments() {

    }

    public Comments(User user, Blog blog, String comment) {
        this.user = user;
        this.blog = blog;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Blog getBlog() {
        return blog;
    }
    public void setBlog(Blog blog) {
        this.blog = blog;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
