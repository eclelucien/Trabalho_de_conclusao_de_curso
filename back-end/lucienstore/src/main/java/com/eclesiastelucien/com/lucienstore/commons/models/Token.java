package com.eclesiastelucien.com.lucienstore.commons.models;

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
public class Token implements Serializable {
        @JsonProperty("accessToken")
        private @NotNull String accessToken;
        @JsonProperty("expiration")
        private String expiration;
}
