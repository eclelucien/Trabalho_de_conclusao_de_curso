package com.eclesiastelucien.com.lucienstore.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.eclesiastelucien.com.lucienstore.configs.jwt.JwtAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;




@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private static final String[] GUEST_ROUTES_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/h2-console/**",
            "/error/**",
            "/upload-dir/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(GUEST_ROUTES_LIST)
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/products/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/categories/**")
                .permitAll()
                .requestMatchers("/api/v1/countries/**")
                .permitAll()
                .requestMatchers("/api/v1/promotions/**")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/sellers")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/shipments/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET,  "/api/v1/users")
                .permitAll()
                .requestMatchers(HttpMethod.POST,  "/api/v1/users")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
