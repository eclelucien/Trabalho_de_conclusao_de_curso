package com.eclesiastelucien.com.lucienstore.commons.modules.auth;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Service;

import com.eclesiastelucien.com.lucienstore.commons.dtos.AuthenticationResponse;
import com.eclesiastelucien.com.lucienstore.commons.models.Token;
import com.eclesiastelucien.com.lucienstore.commons.modules.auth.dtos.requests.AuthenticationRequest;
import com.eclesiastelucien.com.lucienstore.commons.utils.BaseServiceImpl;
import com.eclesiastelucien.com.lucienstore.configs.jwt.JwtService;
import com.eclesiastelucien.com.lucienstore.modules.user.UserRepository;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

@Primary
@Service
public class AuthenticationServiceImpl extends BaseServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        logger.info("[authenticate] - request parameters: " + authenticationRequest.getEmail());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()));

        User user = this.userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();

        logger.info("[authenticate] - found user id: " + user.getId());

        String jwtToken = jwtService.generateToken(user);
        this.updateToken(jwtToken, user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .user(user)
                .build();
    }

    public User me() {
        return this.authenticatedUser();
    }

    @Override
    public void resetPassword(String email) {
        logger.info("[resetPassword] - request parameters: " + email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String resetToken = generateResetToken();
            user.setResetPasswordToken(resetToken);
            user.setResetPasswordTokenExpiry(LocalDateTime.now().plusHours(1));
            userRepository.save(user);

            return;
        }
        throw new UnsupportedOperationException("User not found or unsupported user type");
    }

    @Override
    public void updatePassword(String resetToken, String newPassword) {
        Optional<User> userOptional = userRepository.findByResetPasswordToken(resetToken);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getResetPasswordTokenExpiry() != null
                    && user.getResetPasswordTokenExpiry().isAfter(LocalDateTime.now())) {
                user.setPassword(newPassword);
                user.setResetPasswordToken(null);
                user.setResetPasswordTokenExpiry(null);
                userRepository.save(user);
                return;
            }
            throw new UnsupportedOperationException("Reset token has expired");

        }
        throw new UnsupportedOperationException("Invalid or expired reset token");
    }

    @Override
    public void logout() {
        this.updateToken(null, this.authenticatedUser());
    }

    private String generateResetToken() {
        String resetToken = UUID.randomUUID().toString().replace("-", "");

        LocalDateTime resetTokenExpiry = LocalDateTime.now().plusHours(1);

        User user = authenticatedUser();
        user.setResetPasswordToken(resetToken);
        user.setResetPasswordTokenExpiry(resetTokenExpiry);
        userRepository.save(user);

        return resetToken;
    }

    private void updateToken(String token, User user) {
        user.setToken(Token.builder().accessToken(token).build());
        this.userRepository.save(user);
    }

}
