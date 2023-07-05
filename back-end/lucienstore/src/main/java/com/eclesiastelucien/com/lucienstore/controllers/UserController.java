package com.eclesiastelucien.com.lucienstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eclesiastelucien.com.lucienstore.dtos.AuthenticationResponse;
import com.eclesiastelucien.com.lucienstore.dtos.UserRequest;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "users", description = "CRUD REST APIs for user of type administrator or buyer - Create User, Update User, Get User, Get All users, Delete User")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<AuthenticationResponse> create(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>( new AuthenticationResponse(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<String> findAll() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> findById(@PathVariable Long userId) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{userId}")
    public ResponseEntity<String> findAll(@PathVariable Long userId) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
