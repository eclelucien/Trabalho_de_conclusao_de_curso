package com.eclesiastelucien.com.lucienstore.models.order;


import java.util.List;

import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.Property;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.eclesiastelucien.com.lucienstore.models.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

    @EmbeddedId
    private OrderItemKey id = new OrderItemKey();

    @JsonIgnore
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @NonNull
    private Integer quantity;

    @NonNull
    private Double price;

    private Long shipmentId;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<Property> properties;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;

    // @OneToMany(mappedBy = "orderItem")
    // private List<OrderItemHistory> histories = new ArrayList<>();

    public OrderItem(Order order, Product product, Integer quantity, Double price, Long shipmentId,
            List<Property> properties) {
        id.setOrder(order);
        id.setProduct(product);
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
        this.shipmentId = shipmentId;
        this.properties = properties;
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Double getSubTotal() {
        return this.price * this.quantity;
    }
}
