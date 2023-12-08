package com.eclesiastelucien.com.lucienstore.modules.product.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.eclesiastelucien.com.lucienstore.commons.models.AbstractEntity;
import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;
import com.eclesiastelucien.com.lucienstore.modules.order.orderItem.models.OrderItem;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.Shipment;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class Product extends AbstractEntity {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    private List<String> images;

    @NotNull
    private int availableAmount;

    private int soldAmount;

    @ManyToOne
    private Category category;

    private String currency;

    @JdbcTypeCode(SqlTypes.JSON)
    @NotNull
    private Discount discount;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<Property> properties;

    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "products_shipments", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "shipment_id", referencedColumnName = "id"))
    private List<Shipment> shipments;

    @JsonIgnore
    public boolean hasValidDiscount() {
        LocalDate currentDate = LocalDate.now();
        LocalDate discountStartDate = this.getDiscount().getStartDate();
        LocalDate discountEndDate = this.getDiscount().getEndDate();

        return this.getDiscount().getValue() > 0 && discountStartDate != null &&
                discountEndDate != null
                && !currentDate.isBefore(discountStartDate)
                && !currentDate.isAfter(discountEndDate);
    }
}
