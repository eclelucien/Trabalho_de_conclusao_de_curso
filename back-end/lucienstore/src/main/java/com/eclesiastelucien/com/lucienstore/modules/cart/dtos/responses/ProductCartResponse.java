package com.eclesiastelucien.com.lucienstore.modules.cart.dtos.responses;

import com.eclesiastelucien.com.lucienstore.modules.product.dtos.ProductResponse;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Product;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartResponse extends ProductResponse {
    private User seller;

    public ProductCartResponse(Product product) {
        super(product);
        this.seller = product.getSeller();
    }
}
