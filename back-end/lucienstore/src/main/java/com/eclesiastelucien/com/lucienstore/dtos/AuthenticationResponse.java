package com.eclesiastelucien.com.lucienstore.dtos;

import com.eclesiastelucien.com.lucienstore.models.User;

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
