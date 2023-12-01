package com.eclesiastelucien.com.lucienstore.commons.modules.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eclesiastelucien.com.lucienstore.commons.dtos.ApiResponse;
import com.eclesiastelucien.com.lucienstore.commons.dtos.AuthenticationResponse;
import com.eclesiastelucien.com.lucienstore.commons.modules.auth.dtos.requests.AuthenticationRequest;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "authentication", description = "Authentication management - Create user token, get user by token, Reset password, update password")
@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(this.authenticationServiceImpl.authenticate(authenticationRequest), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public ResponseEntity<User> me() {
        return new ResponseEntity<>(this.authenticationServiceImpl.me(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logout() {
        this.authenticationServiceImpl.logout();
        return new ResponseEntity<>(new ApiResponse(true, ""), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/password/reset")
    public ResponseEntity<ApiResponse> resetPassword(@RequestParam("email") String email) {
        this.authenticationServiceImpl.resetPassword(email);
        return new ResponseEntity<>(new ApiResponse(true, ""), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/password/update")
    public ResponseEntity<ApiResponse> updatePassword(@RequestParam("token") String resetToken,
            @RequestParam("password") String newPassword) {
        authenticationServiceImpl.updatePassword(resetToken, newPassword);
        return new ResponseEntity<>(new ApiResponse(true, "Password updated successfully"), HttpStatus.OK);
    }
}
