package com.eclesiastelucien.com.lucienstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eclesiastelucien.com.lucienstore.dtos.product.ProductDetailResponse;
import com.eclesiastelucien.com.lucienstore.dtos.product.ProductRequest;
import com.eclesiastelucien.com.lucienstore.dtos.product.ProductResponse;
import com.eclesiastelucien.com.lucienstore.models.product.Product;
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

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        Product createdProduct = productService.createProduct(productRequest);

        ProductResponse createdProductResponse = new ProductResponse(createdProduct);
        return new ResponseEntity<>(createdProductResponse, HttpStatus.CREATED);
    }

 
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        Product updatedProduct = productService.updateProduct(productId, productRequest);

        ProductResponse updatedProductResponse = new ProductResponse(updatedProduct);
        return new ResponseEntity<>(updatedProductResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}

