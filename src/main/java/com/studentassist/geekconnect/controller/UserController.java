package com.studentassist.geekconnect.controller;

import com.studentassist.geekconnect.Response.LoginResponse;
import com.studentassist.geekconnect.Response.UserResponse;
import com.studentassist.geekconnect.model.User;
import com.studentassist.geekconnect.responsemodel.UserResponseModel;
import com.studentassist.geekconnect.repository.UserRepository;
import com.studentassist.geekconnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/geekconnect/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody User loginUser) {
        // Find user by username
        User user = userRepository.findByUsername(loginUser.getUsername());
        // Check if user exists and password matches
        LoginResponse response = new LoginResponse();
        if (user != null && loginUser.getPassword().equals(user.getPassword())) {
            response.setMessage("Login successful!");
            response.setID(user.getId());
            response.setUserName(user.getUsername());
            response.setStatus(HttpStatus.OK);
            System.out.println(response);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } else {
            response.setMessage("Invalid username or password");
//            response.setUser(null);
            response.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response);
        }
    }


    @CrossOrigin
    @GetMapping("/id")
    public ResponseEntity<UserResponse> getUserInfo(@RequestParam String id) {
        // Check if user exists and password matches
        UserResponse response = new UserResponse();
        try {
            // Find user by username
            UserResponseModel user = userService.getUserById(id);
            response.setMessage("Fetched User details successful!");
            response.setObject(user);
            response.setStatus(HttpStatus.OK);
            System.out.println(response);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (Exception e){
            response.setMessage("User not found");
            response.setObject(null);
            response.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response);
        }
    }

    @CrossOrigin
    @PutMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody User newUser) {
        // Find user by username
        User user = userRepository.findByUsername(newUser.getUsername());
        // Check if user exists and password matches
        UserResponse response = new UserResponse();
        if (user==null) {
            String userId = UUID.randomUUID().toString();
            newUser.setId(userId);
            user = userRepository.save(newUser);
            response.setMessage("User registered successfully!");
            response.setObject(new UserResponseModel(user));
            response.setStatus(HttpStatus.OK);
            System.out.println(response);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } else {
            response.setMessage("Username already exists");
            response.setObject(null);
            response.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(response);
        }
    }





//
//    @GetMapping("/all")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }

//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        User registeredUser = userService.registerUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        User updatedUser = userService.updateUser(id, user);
//        if (updatedUser != null) {
//            return ResponseEntity.ok(updatedUser);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
}
