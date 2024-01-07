package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;

@Service
public class BlogService {
	        @Autowired
	       private BlogRepository repo;     

	       public List<Blog> getAccounts(){
	            return (List<Blog>) repo.findAll();
	        }

}
