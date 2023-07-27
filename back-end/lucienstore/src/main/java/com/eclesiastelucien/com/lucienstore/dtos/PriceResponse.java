package com.eclesiastelucien.com.lucienstore.dtos;


import com.eclesiastelucien.com.lucienstore.models.product.Product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponse {
    private @NotNull FormattedPrice before;
    private @NotNull FormattedPrice current;

    public PriceResponse(Product product) {
        FormattedPrice before = new FormattedPrice(product.getPrice(),
                "$" + " " + product.getPrice(),
                product.getDiscount().getValue() * 100 / product.getPrice());

        this.setBefore(before);

        FormattedPrice current = new FormattedPrice(product.getPrice(),
                "$" + " " + product.getPrice(), 0.0);
        this.setCurrent(current);
    }

    public static PriceResponse fromPrice(Double price) {
        PriceResponse dto = new PriceResponse();
        dto.setCurrent(new FormattedPrice(price, null, price));
        return dto;
    }
}