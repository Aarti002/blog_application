package com.example.demo.model;

import jakarta.persistence.*;
import java.util.*;


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
    
    @ElementCollection(targetClass = Tags.class)
    @CollectionTable(name = "blog_tags", joinColumns = @JoinColumn(name = "blog_id"))
    @Enumerated(EnumType.STRING)
    private Set<Tags> tags;

    public Blog() {

    }

    public Blog(String title, String content, User user, Set<Tags> tags) {
        this.title = title;
        this.content = content;
        this.user=user;
        this.tags=tags;
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
    public void setTags(Set<Tags> allTags) {
    	this.tags=allTags;
    }
    public Set<Tags> getTags(){
    	return tags;
    }
}
