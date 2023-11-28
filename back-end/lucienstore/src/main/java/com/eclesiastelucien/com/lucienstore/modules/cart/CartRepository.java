package com.eclesiastelucien.com.lucienstore.modules.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eclesiastelucien.com.lucienstore.modules.order.enums.OrderStatusEnum;
import com.eclesiastelucien.com.lucienstore.modules.order.models.Order;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import java.util.List;

public interface CartRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByBuyerAndStatus(User buyer, OrderStatusEnum status);
}
