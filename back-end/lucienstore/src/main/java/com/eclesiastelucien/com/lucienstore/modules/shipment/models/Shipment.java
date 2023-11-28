
package com.eclesiastelucien.com.lucienstore.modules.shipment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Currency;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.eclesiastelucien.com.lucienstore.commons.models.AbstractEntity;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Discount;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Product;
import com.eclesiastelucien.com.lucienstore.modules.shipment.enums.ShipmentTypeEnum;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shipments")
public class Shipment extends AbstractEntity {

    @NotNull
    private String description;
    @NotNull
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ShipmentTypeEnum type;

    @ManyToOne
    private User seller;

    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    private Currency currency;

    @JdbcTypeCode(SqlTypes.JSON)
    private Discount discount;

    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    private ShipmentDeadline deadline;

    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    private CoverageArea coverageArea;

    @JsonIgnore
    @ManyToMany(mappedBy = "shipments")
    private List<Product> products;

}
