package com.eclesiastelucien.com.lucienstore.modules.category;

import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;

import java.util.List;

public interface CategoryService {
    void create(String name);

    List<Category> findAll();

    Category findById(Long id);

    void update(Long id, String name);

    void remove(Long id);
}
