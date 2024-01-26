package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.demo.repository.*;
import com.example.demo.model.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsRepository commentRepo;

    @Autowired
    public CommentsController(CommentsRepository commentsRepository) {
        this.commentRepo = commentsRepository;
    }
    
    @GetMapping("/allComment")
    public List<Comments> getAllComments() {
    	List<Comments> response=commentRepo.findAll();
        return response;
    }

    @GetMapping("/commentDetail/{id}")
    public ResponseEntity<Comments> getCommentById(@PathVariable int id) {
        Comments comment = commentRepo.findById(id).get();
        if(Objects.isNull(comment))
        	return null;
        return ResponseEntity.ok(comment);
    }
    
    @PostMapping("/createComment")
    public ResponseEntity<Comments> addComment(@RequestBody Comments comment){
    	Comments newComment=new Comments();
    	newComment.setUser(comment.getUser());
    	newComment.setBlog(comment.getBlog());
    	newComment.setComment(comment.getComment());
    	commentRepo.save(comment);
    	return ResponseEntity.ok(newComment);
    }
    
    @GetMapping("/allCommentsFromUser/{id}")
    public List<Comments> allCommentsFromUser(@PathVariable int id){
    	List<Comments> allComments=commentRepo.findByUserId(id);
    	return allComments;
    }
    
    @GetMapping("/allCommentsUnderBlog/{id}")
    public List<Comments> allCommentsUnderBlog(@PathVariable int id){
    	List<Comments> allComments=commentRepo.findByBlogId(id);
    	return allComments;
    }
    
    @GetMapping("/editCommentDetail/{id}")
    public ResponseEntity<Comments> editCommentDetail(@PathVariable int id,@RequestBody Comments editComment) {
        Comments comment = commentRepo.findById(id).get();
        if(Objects.isNull(comment))
        	return null;
        if(Objects.nonNull(editComment.getUser()) && Objects.nonNull(editComment.getUser().getId())) {
        	comment.setUser(editComment.getUser());
        }
        if(Objects.nonNull(editComment.getBlog()) && Objects.nonNull(editComment.getBlog().getId())) {
  		  	comment.setBlog(editComment.getBlog());
  		}
        if(Objects.nonNull(editComment.getComment()) && !"".equalsIgnoreCase(editComment.getComment())) {
            comment.setComment(editComment.getComment());
        }
        commentRepo.save(comment);
        return ResponseEntity.ok(comment);
    }
    
    @DeleteMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable("id") int id) {
  	  Comments comment=commentRepo.findById(id).get();
  	  if(Objects.isNull(comment)) {
  		  return null;
  	  }
  	  commentRepo.deleteById(id);
  	  return "Successfully Deleted this Comment!";
    }
    

    
}
