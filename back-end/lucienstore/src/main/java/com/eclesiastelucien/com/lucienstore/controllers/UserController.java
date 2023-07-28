package com.eclesiastelucien.com.lucienstore.controllers;

import com.eclesiastelucien.com.lucienstore.dtos.UserRequest;
import com.eclesiastelucien.com.lucienstore.models.User;
import com.eclesiastelucien.com.lucienstore.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "products", description = "CRUD REST APIs - Create Product, Update Product, Get Product, Get All products, Delete Product")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserRequest userRequest) {
        User createdUser = userService.createUser(userRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
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
