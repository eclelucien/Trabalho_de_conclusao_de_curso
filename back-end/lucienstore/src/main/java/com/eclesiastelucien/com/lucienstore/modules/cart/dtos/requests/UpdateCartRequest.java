package com.eclesiastelucien.com.lucienstore.modules.cart.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartRequest {
    @NotNull(message = "quantity cannot be null")
    private int quantity;
}
