package com.eclesiastelucien.com.lucienstore.commons.dtos;

import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private @NotNull String token;
    private @NotNull User user;
}
