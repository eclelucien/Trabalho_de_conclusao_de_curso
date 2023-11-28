package com.eclesiastelucien.com.lucienstore.modules.order.orderItem.responses;

import java.io.Serializable;

import com.eclesiastelucien.com.lucienstore.modules.product.dtos.ProductDetailResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse implements Serializable {
    private ProductDetailResponse product;
    private Integer quantity;
    private Double price;
    private Double subTotal;
}
