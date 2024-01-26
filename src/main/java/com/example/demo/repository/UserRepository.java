package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.User;
import com.example.demo.request.CreateUserRequest;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	//User createUser(CreateUserRequest createUserRequest);
		
}
