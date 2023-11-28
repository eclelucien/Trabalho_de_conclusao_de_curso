package com.eclesiastelucien.com.lucienstore.modules.category.models;

import java.util.List;

import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.eclesiastelucien.com.lucienstore.commons.models.AbstractEntity;
import com.eclesiastelucien.com.lucienstore.modules.category.dtos.requests.CategoryRequest;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {

    @NotNull
    private String name;
    private String image;
    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> tags;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(CategoryRequest categoryRequest) {
        this.setName(categoryRequest.getName());
        this.setTags(categoryRequest.getTags());
    }

}
