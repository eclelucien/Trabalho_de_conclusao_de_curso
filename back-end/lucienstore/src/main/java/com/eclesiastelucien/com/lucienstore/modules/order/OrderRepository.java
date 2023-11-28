package com.eclesiastelucien.com.lucienstore.modules.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eclesiastelucien.com.lucienstore.modules.order.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByPaymentIntent(String paymentIntent);

    @Query(value = "SELECT * FROM orders WHERE status = :status", nativeQuery = true)
    List<Order> findByStatus(String status);

    @Query(value = "SELECT * FROM orders WHERE created_at = :date", nativeQuery = true)
    List<Order> findByDate(@Param("date") LocalDateTime date);

    @Query(value = "SELECT * FROM orders WHERE buyer_id = :id", nativeQuery = true)
    List<Order> findByBuyerOrSeller(@Param("id") Long id);
}
