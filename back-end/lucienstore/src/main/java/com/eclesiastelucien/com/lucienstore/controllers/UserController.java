package com.eclesiastelucien.com.lucienstore.controllers;

import com.eclesiastelucien.com.lucienstore.dtos.ApiResponse;
import com.eclesiastelucien.com.lucienstore.dtos.AuthenticationResponse;
import com.eclesiastelucien.com.lucienstore.dtos.UserRequest;
import com.eclesiastelucien.com.lucienstore.models.User;
import com.eclesiastelucien.com.lucienstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> create(@RequestBody UserRequest userRequest) {
        User newUser = new User();
        // Définir les propriétés de newUser à partir de userRequest
        newUser.setName(userRequest.getName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        newUser.setPassword(userRequest.getPassword());

        User createdUser = userService.createUser(newUser);
        // Vous pouvez ajouter des détails supplémentaires à la réponse, si nécessaire
        AuthenticationResponse response = new AuthenticationResponse();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    
}
