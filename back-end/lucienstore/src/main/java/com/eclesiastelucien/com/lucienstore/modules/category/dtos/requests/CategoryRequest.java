package com.eclesiastelucien.com.lucienstore.modules.category.dtos.requests;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryRequest {

    @NotNull(message = "name cannot be null")
    private String name;
}
