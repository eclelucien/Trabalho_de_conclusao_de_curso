package com.eclesiastelucien.com.lucienstore.modules.category;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
