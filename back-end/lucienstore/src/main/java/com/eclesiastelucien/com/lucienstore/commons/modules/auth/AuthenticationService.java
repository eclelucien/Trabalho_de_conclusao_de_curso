package com.eclesiastelucien.com.lucienstore.commons.modules.auth;

import com.eclesiastelucien.com.lucienstore.commons.dtos.AuthenticationResponse;
import com.eclesiastelucien.com.lucienstore.commons.modules.auth.dtos.requests.AuthenticationRequest;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

public interface AuthenticationService {

        public User me();

        public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

        public void resetPassword(String email);

        public void logout();

        public void updatePassword(String resetToken, String newPassword);

}
