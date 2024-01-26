package com.example.demo.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.mockito.*;

import org.mockito.junit.*;
import org.springframework.http.*;
import org.junit.*;
import org.junit.runner.RunWith;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.CreateUserRequest;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetUserById() {
        int userId = 1;
        User mockUser = new User("John Doe","john@gmail.com");

        when(userRepo.findById(userId).get()).thenReturn(mockUser);
        ResponseEntity<User> responseEntity = userController.getUserById(userId);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(mockUser);
    }

//    @Test
//    public void testCreateUser() {
//        CreateUserRequest createUserRequest = new CreateUserRequest("john singh", "johnSingh@gmail.com");
//        User createdUser = new User("john singh", "johnSingh@gmail.com");
//
//        when(userRepo.createUser(createUserRequest)).thenReturn(createdUser);
//        ResponseEntity<User> responseEntity = userController.addUser(createdUser);
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(responseEntity.getBody()).isEqualTo(createdUser);
//    }
    
    @Test
    public void testUpdateUserDetails() {
    	int employeeId = 1;
        User savedUser = new User("rameshK","ramesh@gmail.com");
        User updatedUser =  new User("Priya","priyaShree@gmail.com");
        when(userRepo.findById(employeeId).get()).thenReturn(savedUser);
        ResponseEntity<User> responseEntity = userController.editUserDetail(employeeId, updatedUser);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(updatedUser);
    }

}
