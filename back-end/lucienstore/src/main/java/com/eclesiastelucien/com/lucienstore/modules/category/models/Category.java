package com.eclesiastelucien.com.lucienstore.modules.category.models;

import com.eclesiastelucien.com.lucienstore.commons.models.AbstractEntity;
import lombok.*;

import jakarta.persistence.Entity;
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

}
