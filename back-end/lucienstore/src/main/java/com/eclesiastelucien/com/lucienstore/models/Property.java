package com.eclesiastelucien.com.lucienstore.models;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record Property(@NotNull String key, @NotNull String value, int quantity)
        implements Serializable {
}
