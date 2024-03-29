package com.eclesiastelucien.com.lucienstore.modules.product.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Product;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Property;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse extends ProductResponse {

    private int availableAmount;
    private int soldAmount;
    private List<String> availableCountries = new ArrayList<>();
    private Map<String, List<Property>> properties;
    private List<ProductResponse> relatedProducts;
    private Category category;

    public ProductDetailResponse(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setAvailableAmount(product.getAvailableAmount());
        this.setDescription(product.getDescription());
        this.setPrices(this.getPricesDto(product));
        this.setImages(product.getImages());
        this.setProperties(this.propertyResponse(product.getProperties()));
        this.setCategory(product.getCategory());
    }

}
