package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import com.example.demo.repository.*;
import com.example.demo.model.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogRepository blogRepo;

    @Autowired
    public BlogController(BlogRepository blogRepository) {
        this.blogRepo = blogRepository;
    }
    
    @GetMapping("/allBlogs")
    public List<Blog> getAllBlogs() {
    	List<Blog> response=blogRepo.findAll();
        return response;
    }

    @GetMapping("/blogDetail/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id) {
        Blog blog = blogRepo.findById(id).get();
        if(Objects.isNull(blog))
        	return null;
        return ResponseEntity.ok(blog);
    }
    
    @PostMapping("/createBlog")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog){
    	Blog newBlog=new Blog();
    	  newBlog.setTitle(blog.getTitle());
    	  newBlog.setContent(blog.getContent());
    	  newBlog.setUser(blog.getUser());
    	  blogRepo.save(blog);
    	  return ResponseEntity.ok(newBlog);
    }
    
    @GetMapping("/allBlogsFromUser/{id}")
    public List<Blog> allBlogsFromUser(@PathVariable int id){
    	List<Blog> allBlogs=blogRepo.findByUserId(id);
    	return allBlogs;
    }
    
    @GetMapping("/editBlogDetail/{id}")
    public ResponseEntity<Blog> editBlogDetail(@PathVariable int id,@RequestBody Blog editBlog) {
        Blog blog = blogRepo.findById(id).get();
        if(Objects.isNull(blog))
        	return null;
        if(!Objects.isNull(editBlog.getTitle()) && !"".equalsIgnoreCase(editBlog.getTitle())) {
        	blog.setTitle(editBlog.getTitle());
        }
        if(Objects.nonNull(editBlog.getContent()) && !"".equalsIgnoreCase(editBlog.getContent())) {
  		  	blog.setContent(editBlog.getContent());
  		}
        if(Objects.nonNull(editBlog.getUser()) && Objects.nonNull(editBlog.getUser().getId())) {
            blog.getUser().setId(editBlog.getUser().getId());
        }
        blogRepo.save(blog);
        return ResponseEntity.ok(blog);
    }
    
    @DeleteMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable("id") int id) {
  	  Blog blog=blogRepo.findById(id).get();
  	  if(Objects.isNull(blog)) {
  		  return null;
  	  }
  	  blogRepo.deleteById(id);
  	  return "Successfully Deleted this blog!";
    }

    
}
