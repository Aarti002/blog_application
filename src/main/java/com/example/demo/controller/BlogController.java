package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.demo.repository.*;
import com.example.demo.model.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogRepository blogRepo;
    private final CommentsRepository commentRepo;

    @Autowired
    public BlogController(BlogRepository blogRepository, CommentsRepository commentRepository) {
        this.blogRepo = blogRepository;
        this.commentRepo = commentRepository;
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
    	  newBlog.setTags(blog.getTags());
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
        if(Objects.nonNull(editBlog.getTags())) {
            blog.setTags(editBlog.getTags());
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
  	  else {
		  List<Comments> comments=commentRepo.findByBlogId(blog.getId());
		  for(Comments comment:comments) {
			  commentRepo.delete(comment);
		  }
	  }
  	  blogRepo.deleteById(id);
  	  return "Successfully Deleted this blog!";
    }
    
    @GetMapping("/filterByTags/{tag}")
    public List<Blog> filterByTag(@PathVariable("tag") String tagName){
    	Tags tag;
    	List<Blog> allBlogs=blogRepo.findAll();
    	List<Blog> result = new ArrayList<>();
    	try {
            tag = Tags.valueOf(tagName);
            
        	for(Blog item:allBlogs) {
        		Set<Tags> allTags=item.getTags();
        		if(allTags.contains(tag))
        			result.add(item);
        	}
        	return result;
        } catch (IllegalArgumentException e) {
        	throw new IllegalArgumentException(e);
        }
    	
    }
    
    @GetMapping("/getBlogComments/{id}")
    public List<String> getBlogComments(@PathVariable("id") int id){
    	List<Comments> allComments=commentRepo.findByBlogId(id);
    	List<String> result=new ArrayList<String>();
    	for(Comments item:allComments) {
    		result.add(item.getComment());
    	}
    	return result;
    }
    

    
}
