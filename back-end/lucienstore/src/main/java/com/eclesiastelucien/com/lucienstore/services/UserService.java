package com.eclesiastelucien.com.lucienstore.services;

import com.eclesiastelucien.com.lucienstore.dtos.UserRequest;
import com.eclesiastelucien.com.lucienstore.models.User;
import com.eclesiastelucien.com.lucienstore.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserRequest userRequest) {
        User newUser = new User();
        newUser.setName(userRequest.getName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        newUser.setPassword(userRequest.getPassword());        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Un utilisateur avec cet e-mail existe déjà.");
        }
        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID spécifié."));
    }

    public User updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID spécifié."));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    
}
