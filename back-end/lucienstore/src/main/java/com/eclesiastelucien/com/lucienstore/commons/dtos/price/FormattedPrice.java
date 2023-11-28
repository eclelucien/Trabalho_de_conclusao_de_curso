package com.eclesiastelucien.com.lucienstore.commons.dtos.price;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FormattedPrice(@NotNull Double raw, @NotNull String formatted, Double discountPercent)
                implements Serializable {
}
