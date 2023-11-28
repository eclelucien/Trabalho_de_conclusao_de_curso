package com.eclesiastelucien.com.lucienstore.modules.product.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchResponse {
    private @NotNull Long id;
    private @NotNull String name;
    private @NotNull String description;
    private List<String> images;
    private @NotNull int availableAmount;
}
