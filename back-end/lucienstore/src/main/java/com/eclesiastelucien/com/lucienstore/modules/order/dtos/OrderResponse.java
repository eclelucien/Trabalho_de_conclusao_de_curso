package com.eclesiastelucien.com.lucienstore.modules.order.dtos;

import java.io.Serializable;
import java.util.List;

import com.eclesiastelucien.com.lucienstore.modules.order.enums.OrderStatus;
import com.eclesiastelucien.com.lucienstore.modules.order.orderItem.responses.OrderItemResponse;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse implements Serializable {
    private Long id;
    private OrderStatus status;
    private User buyer;
    private List<OrderItemResponse> items;
    private Double total;
}
