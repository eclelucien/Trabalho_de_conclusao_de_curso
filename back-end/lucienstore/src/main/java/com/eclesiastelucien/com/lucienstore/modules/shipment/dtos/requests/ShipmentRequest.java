package com.eclesiastelucien.com.lucienstore.modules.shipment.dtos.requests;

import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;

import com.eclesiastelucien.com.lucienstore.commons.utils.BaseServiceImpl;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Discount;
import com.eclesiastelucien.com.lucienstore.modules.shipment.enums.ShipmentTypeEnum;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.CoverageArea;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.Shipment;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.ShipmentDeadline;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequest {
    @NotNull(message = "description cannot be null")
    private String description;

    @NotNull(message = "price cannot be null")
    private Double price;
    @NotNull
    private ShipmentTypeEnum type;
    @NotNull
    private Currency currency;

    @NotNull(message = "discount cannot be null")
    private Discount discount;

    @NotNull(message = "deadline cannot be null")
    private ShipmentDeadline deadline;

    @NotNull(message = "coverageArea cannot be null")
    private CoverageArea coverageArea;

    @JsonIgnore
    @Autowired
    private BaseServiceImpl baseServiceImpl;

    public Shipment toShipment() {
        Shipment shipment = new Shipment();
        shipment.setDescription(description);
        shipment.setPrice(price);
        shipment.setType(type);
        shipment.setSeller(baseServiceImpl.authenticatedUser());
        shipment.setCurrency(currency);
        shipment.setDiscount(discount);
        shipment.setDeadline(deadline);
        shipment.setCoverageArea(coverageArea);
        return shipment;
    }

}
