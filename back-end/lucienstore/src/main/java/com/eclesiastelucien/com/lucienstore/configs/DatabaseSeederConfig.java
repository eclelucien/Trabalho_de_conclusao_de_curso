package com.eclesiastelucien.com.lucienstore.configs;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.eclesiastelucien.com.lucienstore.modules.user.UserRepository;
import com.eclesiastelucien.com.lucienstore.modules.user.models.Administrator;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile({ "dev", "staging", "prod" })
@RequiredArgsConstructor
public class DatabaseSeederConfig implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Check if a user with the specified email already exists
            String email = "senderlouis@gmail.com";
            Optional<User> existingUser = userRepository.findByEmail(email);

            if (existingUser.isEmpty()) {
                // User does not exist, create and save the new user
                Administrator administrator = new Administrator();
                administrator.setName("Eclesiaste Lucien");
                administrator.setEmail(email);
                administrator.setPhoneNumber("444444444");
                administrator.setActive(true);
                String hashedPassword = passwordEncoder.encode("admin");

                administrator.setPassword(hashedPassword);

                this.userRepository.save(administrator);

                System.out.println("Administrator user saved successfully.");
            } else {
                System.out.println("Administrator user with email " + email + " already exists.");
            }

        } catch (Exception e) {
            throw e; // rethrow the exception to mark the initialization as failed
        }
    }
}
