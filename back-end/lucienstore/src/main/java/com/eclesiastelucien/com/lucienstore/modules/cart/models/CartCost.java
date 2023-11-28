package com.eclesiastelucien.com.lucienstore.modules.cart.models;

import java.io.Serializable;
import java.util.List;

import com.eclesiastelucien.com.lucienstore.modules.cart.dtos.responses.CartResponse;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartCost implements Serializable {
    private List<CartResponse> cartItems;
    private double totalCost;
    private SubTotal productSubtotal;
    private SubTotal shipmentSubtotal;
    private Integer count;
}