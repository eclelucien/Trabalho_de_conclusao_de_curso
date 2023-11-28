package com.eclesiastelucien.com.lucienstore.modules.order;

import java.time.LocalDateTime;
import java.util.List;

import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

public interface OrderManagementService {
    public List<OrderResponse> findAll();

    public OrderResponse findById(Long id);

    public List<OrderResponse> findByStatus(String orderStatus);

    public List<OrderResponse> findByDate(LocalDateTime dateTime);

    public List<OrderResponse> findByBuyerOrSeller(User user);

    public void remove(Long id);
}
