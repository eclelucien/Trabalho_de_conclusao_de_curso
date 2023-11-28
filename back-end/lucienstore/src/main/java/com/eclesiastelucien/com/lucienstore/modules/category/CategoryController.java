package com.eclesiastelucien.com.lucienstore.modules.category;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eclesiastelucien.com.lucienstore.modules.category.dtos.requests.CategoryRequest;
import com.eclesiastelucien.com.lucienstore.modules.category.dtos.responses.CategoryResponse;
import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;

import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Tag(name = "categories", description = "CRUD REST APIs - Create Category, Update Category, Get User, Get All categories, Delete Category")
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ApiResponse(responseCode = "201")
    public ResponseEntity<ApiResponse> create(@RequestPart("categoryRequest") String categoryJson,
            @NotNull @RequestPart("file") MultipartFile file) throws IOException {
        this.categoryServiceImpl.create(Utils.parseJson(categoryJson, CategoryRequest.class), file);
        return new ResponseEntity<>(new ApiResponse(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit) {
        List<Category> categories = this.categoryServiceImpl.findAll(page, limit);
        List<CategoryResponse> categoryResponse = categories.stream().map(CategoryResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> findOneById(@PathVariable @Min(1) Long categoryId) {
        CategoryResponse categoryResponse = new CategoryResponse(this.categoryServiceImpl.findById(categoryId));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> update(@PathVariable @Min(1) Long categoryId,
            @RequestBody CategoryRequest categoryRequestDto) {
        this.categoryServiceImpl.update(categoryId, categoryRequestDto);
        return new ResponseEntity<>(new ApiResponse(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable @Min(1) Long categoryId) {
        this.categoryServiceImpl.remove(categoryId);
        return new ResponseEntity<>(new ApiResponse(), HttpStatus.NO_CONTENT);
    }
}
