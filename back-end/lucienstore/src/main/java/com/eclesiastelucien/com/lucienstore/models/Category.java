package com.eclesiastelucien.com.lucienstore.models;


import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
}
