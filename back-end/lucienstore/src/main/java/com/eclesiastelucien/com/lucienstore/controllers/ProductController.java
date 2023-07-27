package com.eclesiastelucien.com.lucienstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eclesiastelucien.com.lucienstore.dtos.ProductDetailResponse;
import com.eclesiastelucien.com.lucienstore.dtos.ProductResponse;
import com.eclesiastelucien.com.lucienstore.models.product.ProductSearchResponse;
import com.eclesiastelucien.com.lucienstore.services.ProductService;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "products", description = "CRUD REST APIs - Create Product, Update Product, Get Product, Get All products, Delete Product")
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit) {
        List<ProductResponse> productsDto = new ProductResponse()
                .toFormattedList(this.productService.findAll(page, limit));
        return new ResponseEntity<>(productsDto, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponse> productDetail(@PathVariable Long productId) {
        ProductDetailResponse productDetail = new ProductDetailResponse(this.productService.findById(productId));
        return new ResponseEntity<>(productDetail, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductSearchResponse>> productSearch(@RequestParam String query) {
        List<ProductSearchResponse> searchResults = productService.productSearch(query);

        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
}

