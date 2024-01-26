package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comments;
import com.example.demo.repository.CommentsRepository;

@Service
public class CommentsService {
	        @Autowired
	       private CommentsRepository repo;     

	       public List<Comments> getAccounts(){
	            return (List<Comments>) repo.findAll();
	        }

}
