package com.eclesiastelucien.com.lucienstore.models.product;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record Property(@NotNull String key, @NotNull String value, int quantity)
        implements Serializable {
}
