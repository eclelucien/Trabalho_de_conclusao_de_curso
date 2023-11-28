package com.eclesiastelucien.com.lucienstore.modules.product.dtos;

import com.eclesiastelucien.com.lucienstore.commons.dtos.price.FormattedPrice;
import com.eclesiastelucien.com.lucienstore.commons.dtos.price.PriceResponse;
import com.eclesiastelucien.com.lucienstore.commons.utils.Helper;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Product;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse implements Serializable {
    private @NotNull Long id;
    private @NotNull String name;
    private @NotNull String description;
    private @NotNull List<String> images;

    private @NotNull PriceResponse prices;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.images = product.getImages();
        this.prices = getPricesDto(product);
    }

    public List<ProductResponse> toFormattedList(List<Product> products) {

        List<ProductResponse> productResponse = new ArrayList<>();
        for (Product product : products) {
            ProductResponse productDto = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .prices(this.getPricesDto(product))
                    .images(product.getImages())
                    .build();

            productResponse.add(productDto);
        }
        return productResponse;
    }

    protected PriceResponse getPricesDto(Product product) {
        Double discountPercent = 0.0;
        Double currentPrice = product.getPrice();

        if (product.hasValidDiscount()) {
            discountPercent = Helper.formatDecimal(product.getDiscount().getValue() * 100 / product.getPrice());
            currentPrice = Helper.formatDecimal(currentPrice - product.getDiscount().getValue());
        }

        FormattedPrice before = new FormattedPrice(product.getPrice(),
                "$" + " " + product.getPrice(), discountPercent);

        FormattedPrice current = new FormattedPrice(currentPrice,
                "$" + " " + currentPrice, discountPercent);
        return PriceResponse.builder()
                .before(before)
                .current(current)
                .build();
    }

    protected Map<String, List<Property>> propertyResponse(List<Property> properties) {

        Map<String, List<Property>> groupedProperties = new HashMap<>();

        for (Property property : properties) {
            if (!groupedProperties.containsKey(property.key())) {
                List<Property> props = new ArrayList<>();
                props.add(property);
                groupedProperties.put(property.key(), props);
            } else {
                groupedProperties.get(property.key()).add(property);
            }
        }
        return groupedProperties;
    }
}