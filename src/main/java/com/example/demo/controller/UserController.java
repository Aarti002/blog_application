package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepo;
    private final BlogRepository blogRepo;
    private final CommentsRepository commentRepo;

    @Autowired
    public UserController(UserRepository userRepository, BlogRepository blogRepository, CommentsRepository commentRepository) {
        this.userRepo = userRepository;
        this.blogRepo= blogRepository;
        this.commentRepo= commentRepository;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
    	List<User> response=userRepo.findAll();
        return response;
    }

    @GetMapping("/userDetail/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userRepo.findById(id).get();
        if(Objects.isNull(user))
        	return null;
        return ResponseEntity.ok(user);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
  	  User newUser=new User();
  	  newUser.setName(user.getName());
  	  newUser.setEmail(user.getEmail());
  	  userRepo.save(user);
  	  return ResponseEntity.ok(newUser);
    }
    
    @GetMapping("/editUserDetail/{id}")
    public ResponseEntity<User> editUserDetail(@PathVariable int id,@RequestBody User editUser) {
        User user = userRepo.findById(id).get();
        if(Objects.isNull(user))
        	return null;
        if(!Objects.isNull(editUser.getName()) && !"".equalsIgnoreCase(editUser.getName())) {
        	user.setName(editUser.getName());
        }
        if(Objects.nonNull(editUser.getEmail()) && !"".equalsIgnoreCase(editUser.getEmail())) {
  		  	user.setEmail(editUser.getEmail());
  		}
        userRepo.save(user);
        return ResponseEntity.ok(user);
    }
    
    
    
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
  	  User user=userRepo.findById(id).get();
  	  if(Objects.isNull(user)) {
  		  return null;
  	  }
  	  else {
  		  List<Blog> blogs=blogRepo.findByUserId(user.getId());
  		  List<Comments> comments=commentRepo.findByUserId(user.getId());
  		  for(Blog blog:blogs) {
  			  blogRepo.delete(blog);
  		  }
  		  for(Comments comment:comments) {
			  commentRepo.delete(comment);
		  }
  	  }
  	  userRepo.deleteById(id);
  	  return "Successfully Deleted!";
    }
    
    
    
    

    
} 