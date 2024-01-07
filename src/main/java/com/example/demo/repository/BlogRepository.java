package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Blog;
import java.util.*;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    
	List<Blog> findByUserId(int id);
}
