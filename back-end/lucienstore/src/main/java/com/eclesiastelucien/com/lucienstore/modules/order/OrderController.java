package com.eclesiastelucien.com.lucienstore.modules.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eclesiastelucien.com.lucienstore.commons.dtos.ApiResponse;
import com.eclesiastelucien.com.lucienstore.modules.order.models.OrderItem;
import com.eclesiastelucien.com.lucienstore.modules.order.orderItem.enums.OrderItemStatusEnum;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "orders", description = "CRUD REST APIs - Create Order, Update OrderItem (ORDER), Get Order, Get All orders, Delete Order")
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderManagementServiceImpl orderServiceImpl;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return new ResponseEntity<>(this.orderServiceImpl.findAll(), HttpStatus.OK);
    }

    /*
     * @PostMapping
     * public ResponseEntity<NuvannApiResponse> placeOrder(@RequestBody
     * PlaceOrderRequest placeOrderRequest) {
     * // this.orderServiceImpl.placeOrder(placeOrderRequest);
     * return new ResponseEntity<>(new NuvannApiResponse(), HttpStatus.OK);
     * }
     */

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findAll(@PathVariable Long orderId) {
        return new ResponseEntity<>(this.orderServiceImpl.findById(orderId), HttpStatus.OK);
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> updateOrderItemStatusEnum(
            @PathVariable Long orderItemId,
            @RequestParam Long orderId,
            @RequestParam OrderItemStatusEnum newStatus) {
        OrderItem updatedOrderItem = orderServiceImpl.updateOrderItemStatusEnum(orderItemId, orderId, newStatus);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long orderId) {
        orderServiceImpl.remove(orderId);
        return new ResponseEntity<>(new ApiResponse(), HttpStatus.OK);
    }
}
