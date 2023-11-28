package com.eclesiastelucien.com.lucienstore.modules.product.dtos;

import com.eclesiastelucien.com.lucienstore.modules.product.models.Discount;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Property;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductRequest {

    private @NotNull Long categoryId;
    private @NotNull String name;
    private @NotNull String description;
    private @NotNull Double price;
    private @NotNull int availableAmount;

    private @NotNull boolean useDefaultCurrency;

    private List<String> images;

    private Discount discount;

    private List<Property> properties;
}