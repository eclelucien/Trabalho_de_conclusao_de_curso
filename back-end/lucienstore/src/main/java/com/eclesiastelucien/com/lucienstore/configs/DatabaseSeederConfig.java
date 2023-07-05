package com.eclesiastelucien.com.lucienstore.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import lombok.RequiredArgsConstructor;

@Configuration
@Profile({ "dev", "staging", "prod" })
@RequiredArgsConstructor
public class DatabaseSeederConfig implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}