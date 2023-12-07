package com.eclesiastelucien.com.lucienstore.modules.category.dtos.responses;

import java.util.List;

import com.eclesiastelucien.com.lucienstore.commons.dtos.AbstractResponse;
import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse extends AbstractResponse {
        private @NotNull Long id;
        private @NotNull String name;

        public CategoryResponse(Category category) {
                this.setId(category.getId());
                this.setName(category.getName());

        }
}
