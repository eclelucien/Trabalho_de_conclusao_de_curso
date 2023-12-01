package com.eclesiastelucien.com.lucienstore.commons.modules.auth.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken implements Serializable {
        @JsonProperty("accessToken")
        private @NotNull String accessToken;
        @JsonProperty("expiration")
        private String expiration;
}
