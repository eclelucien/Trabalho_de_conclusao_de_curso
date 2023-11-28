package com.eclesiastelucien.com.lucienstore.modules.user.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private @NotNull String name;
    private @NotNull String email;
    private @NotNull String password;
    private @NotNull String phoneNumber;
    private @NotNull boolean termAndConditionAccepted;
    private String addressRequest;
}
