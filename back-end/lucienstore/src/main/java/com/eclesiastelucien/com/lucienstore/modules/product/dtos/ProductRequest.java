package com.eclesiastelucien.com.lucienstore.modules.product.dtos;

import com.eclesiastelucien.com.lucienstore.modules.product.models.Discount;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Property;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

// ProductRequest DTO
@Getter
public class ProductRequest {

    @NotNull
    private Long categoryId;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private int availableAmount;

    @NotNull
    private boolean useDefaultCurrency;

    private List<String> images;

    @NotNull
    private Discount discount;

    private List<Property> properties;
}
