package com.eclesiastelucien.com.lucienstore.services;

import com.eclesiastelucien.com.lucienstore.models.User;
import com.eclesiastelucien.com.lucienstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        // Vérifier si l'utilisateur existe déjà dans la base de données
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Un utilisateur avec cet e-mail existe déjà.");
        }

        // Hasher le mot de passe avant de l'enregistrer dans la base de données
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // Enregistrer l'utilisateur dans la base de données
        return userRepository.save(user);
    }

    
}
