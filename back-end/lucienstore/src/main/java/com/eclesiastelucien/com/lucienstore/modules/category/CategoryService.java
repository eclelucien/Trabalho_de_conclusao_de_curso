package com.eclesiastelucien.com.lucienstore.modules.category;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eclesiastelucien.com.lucienstore.modules.category.dtos.requests.CategoryRequest;
import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;

public interface CategoryService {
    public void create(CategoryRequest categoryRequest, MultipartFile file);

    public List<Category> findAll(int page, int limit);

    public Category findById(Long id);

    public void update(Long id, CategoryRequest categoryRequest);

    public void remove(Long id);
}
