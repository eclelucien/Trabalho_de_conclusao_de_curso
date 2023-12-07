package com.eclesiastelucien.com.lucienstore.modules.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eclesiastelucien.com.lucienstore.modules.product.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT prod.id as id, prod.name as name, prod.description as description, prod.available_amount as availableAmount, prod.images as images "
            +
            "FROM products as prod " +
            "LEFT JOIN categories as cat ON prod.category_id = cat.id " +
            "WHERE (prod.name LIKE %:searchTerm% OR cat.name LIKE %:searchTerm%)", nativeQuery = true)
    List<Object[]> searchProducts(@Param("searchTerm") String searchTerm);

}
