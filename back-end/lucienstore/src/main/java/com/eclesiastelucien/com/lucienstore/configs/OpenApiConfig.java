package com.eclesiastelucien.com.lucienstore.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;

@Configuration
class OpenApiConfig implements WebMvcConfigurer {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI().components(new Components()
                                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP).scheme("bearer")))
                                .info(new Info().title("LucienStore API")
                                                .version("100"));
        }

        @Override
        public void addViewControllers(final ViewControllerRegistry registry) {
                registry.addRedirectViewController("/", "/swagger-ui/index.html");
        }
}