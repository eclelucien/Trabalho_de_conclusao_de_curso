package com.eclesiastelucien.com.lucienstore.modules.cart.models;

import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SubTotal(@NotNull double raw,
        @NotNull String formatted) implements Serializable {

}
