package com.eclesiastelucien.com.lucienstore.modules.category;

import com.eclesiastelucien.com.lucienstore.commons.dtos.ApiResponse;
import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Min;

@Tag(name = "categories", description = "CRUD REST APIs - Create Category, Update Category, Get User, Get All categories, Delete Category")
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestParam String name) {
        this.categoryServiceImpl.create(name);
        return new ResponseEntity<>(new ApiResponse(true, "Category created"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        return new ResponseEntity<>(this.categoryServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findOneById(@PathVariable @Min(1) Long categoryId) {
        return new ResponseEntity<>(this.categoryServiceImpl.findById(categoryId), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> update(@PathVariable @Min(1) Long categoryId, @RequestParam String name) {
        this.categoryServiceImpl.update(categoryId, name);
        return new ResponseEntity<>(new ApiResponse(true, "Category updated"), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable @Min(1) Long categoryId) {
        this.categoryServiceImpl.remove(categoryId);
        return new ResponseEntity<>(new ApiResponse(true, "Category deleted"), HttpStatus.CREATED);
    }
}
