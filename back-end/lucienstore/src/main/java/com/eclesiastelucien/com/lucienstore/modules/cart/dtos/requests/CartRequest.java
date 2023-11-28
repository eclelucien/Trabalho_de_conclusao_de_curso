package com.eclesiastelucien.com.lucienstore.modules.cart.dtos.requests;

import java.util.List;

import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.Property;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    @NotNull(message = "shipmentId cannot be null")
    private Long shipmentId;

    @NotNull(message = "properties cannot be null")
    private List<Property> properties;

    @NotNull(message = "productId cannot be null")
    private Long productId;

    @NotNull(message = "quantity cannot be null")
    private Integer quantity;
}
