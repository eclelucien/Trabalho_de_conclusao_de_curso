package com.eclesiastelucien.com.lucienstore.models.order;


import com.eclesiastelucien.com.lucienstore.enums.OrderStatus;
import com.eclesiastelucien.com.lucienstore.models.AbstractEntity;
import com.eclesiastelucien.com.lucienstore.models.User;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @Column(unique = true)
    private String paymentIntent;

    public Double getTotal() {
        Double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }
}
