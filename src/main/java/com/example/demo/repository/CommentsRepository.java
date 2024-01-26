package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Comments;
import java.util.*;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
    
	List<Comments> findByUserId(int id);
	List<Comments> findByBlogId(int id);
}
