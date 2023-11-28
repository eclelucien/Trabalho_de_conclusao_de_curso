package com.eclesiastelucien.com.lucienstore.commons.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.eclesiastelucien.com.lucienstore.commons.exceptions.ForbiddenResourceException;
import com.eclesiastelucien.com.lucienstore.modules.user.UserRepository;
import com.eclesiastelucien.com.lucienstore.modules.user.enums.UserRoleEnum;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

public class BaseServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public User authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return this.userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ForbiddenResourceException());
    }

    public User authorizedUser() {
        User authUser = this.authenticatedUser();
        if (!authUser.getRole().equals(UserRoleEnum.ADMINISTRATOR)) {
            throw new ForbiddenResourceException();
        }
        return authUser;
    }

}
