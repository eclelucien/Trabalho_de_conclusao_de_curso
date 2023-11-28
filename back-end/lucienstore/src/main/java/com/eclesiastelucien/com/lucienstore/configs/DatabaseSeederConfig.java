package com.eclesiastelucien.com.lucienstore.configs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.eclesiastelucien.com.lucienstore.modules.user.UserRepository;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile({ "dev", "staging", "prod" })
@RequiredArgsConstructor
public class DatabaseSeederConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                User user = new User();
                user.setName("Teste 1");
                user.setEmail("emailteste@gmail.com");
                user.setPhoneNumber("123456");
                if (i > 2) {
                    user.setActive(true);
                } else {
                    user.setActive(false);
                }
                users.add(user);
            }
            userRepository.saveAll(users);
        }
    }
}