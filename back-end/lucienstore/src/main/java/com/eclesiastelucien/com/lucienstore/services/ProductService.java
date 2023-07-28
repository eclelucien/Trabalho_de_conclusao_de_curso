package com.eclesiastelucien.com.lucienstore.services;


import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.eclesiastelucien.com.lucienstore.dtos.product.ProductRequest;
import com.eclesiastelucien.com.lucienstore.exceptions.ResourceNotFoundException;
import com.eclesiastelucien.com.lucienstore.models.product.Product;
import com.eclesiastelucien.com.lucienstore.models.product.ProductSearchResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(int page, int limit) {
        page = page > 0 ? page-- : page;
        PageRequest pageRequest = PageRequest.of(page, limit);
        return productRepository.findAll(pageRequest).getContent();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " was not found."));
    }

   
    public List<Product> getPromotedProducts(int page, int limit) {
          page = page > 0 ? page-- : page;
        PageRequest pageRequest = PageRequest.of(page, limit);
        List<Product> allProducts = productRepository.findAll(pageRequest).getContent();

        List<Product> promotedProducts = new ArrayList<>();

        for (Product product : allProducts) {

            if (product.hasValidDiscount()) {
                promotedProducts.add(product);
            }
        }
        return promotedProducts;
    }

    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setAvailableAmount(productRequest.getAvailableAmount());
        product.setImages(productRequest.getImages());

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " was not found."));

        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setAvailableAmount(productRequest.getAvailableAmount());
        existingProduct.setImages(productRequest.getImages());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with id " + id + " was not found.");
        }
        productRepository.deleteById(id);
    }

    public  List<ProductSearchResponse> productSearch(String query) {
        List<Object[]>matchingProducts =  productRepository.searchProducts(query);

        List<ProductSearchResponse> searchResults = new ArrayList<>();

        for (Object[] productResult : matchingProducts) {
            ProductSearchResponse product = new ProductSearchResponse();
            product.setId(NumberUtils.toLong(productResult[0].toString()));
            product.setName((String) productResult[1]);
            product.setDescription((String) productResult[2]);
            product.setAvailableAmount((int) productResult[3]);
            product.setImages(Arrays.asList((String[]) productResult[4]));
            searchResults.add(product);
        }
        return searchResults;
    }
}
