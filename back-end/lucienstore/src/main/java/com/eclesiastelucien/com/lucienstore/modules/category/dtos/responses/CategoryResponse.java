package com.eclesiastelucien.com.lucienstore.modules.category.dtos.responses;

import java.util.List;

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
        private @NotNull String image;
        private @NotNull List<String> tags;

        public CategoryResponse(Category category) {
                this.setId(category.getId());
                this.setName(category.getName());
                this.setImage(category.getImage());
                this.setTags(category.getTags());
        }
}
