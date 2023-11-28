package com.eclesiastelucien.com.lucienstore.modules.shipment.dtos.responses;

import java.util.Currency;

import com.eclesiastelucien.com.lucienstore.modules.product.models.Discount;
import com.eclesiastelucien.com.lucienstore.modules.shipment.enums.ShipmentTypeEnum;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.CoverageArea;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.Shipment;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.ShipmentDeadline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponse {
    private Long id;
    private String description;
    private Double price;
    private ShipmentTypeEnum type;
    private Long seller;
    private Currency currency;
    private Discount discount;
    private ShipmentDeadline deadline;
    private CoverageArea coverageArea;

    public static ShipmentResponse fromShipment(Shipment shipment) {
        ShipmentResponse responseDTO = new ShipmentResponse();
        responseDTO.setId(shipment.getId());
        responseDTO.setDescription(shipment.getDescription());
        responseDTO.setPrice(shipment.getPrice());
        responseDTO.setType(shipment.getType());
        responseDTO.setSeller(shipment.getSeller().getId());
        responseDTO.setCurrency(shipment.getCurrency());
        responseDTO.setDiscount(shipment.getDiscount());
        responseDTO.setDeadline(shipment.getDeadline());
        responseDTO.setCoverageArea(shipment.getCoverageArea());
        return responseDTO;
    }

}
