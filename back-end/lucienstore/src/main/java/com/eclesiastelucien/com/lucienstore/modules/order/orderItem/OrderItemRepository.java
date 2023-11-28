package com.eclesiastelucien.com.lucienstore.modules.order.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eclesiastelucien.com.lucienstore.modules.order.models.Order;
import com.eclesiastelucien.com.lucienstore.modules.order.orderItem.models.OrderItem;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Product;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemKey> {
    Optional<OrderItem> findByOrderAndProduct(Order order, Product product);
}
