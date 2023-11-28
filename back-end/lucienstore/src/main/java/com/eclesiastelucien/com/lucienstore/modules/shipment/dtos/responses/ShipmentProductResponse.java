package com.eclesiastelucien.com.lucienstore.modules.shipment.dtos.responses;

import java.io.Serializable;

import com.eclesiastelucien.com.lucienstore.commons.dtos.price.FormattedPrice;
import com.eclesiastelucien.com.lucienstore.commons.dtos.price.PriceResponse;
import com.eclesiastelucien.com.lucienstore.modules.shipment.enums.ShipmentTypeEnum;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.Shipment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentProductResponse implements Serializable {
    private Long id;
    private String description;
    private String country;
    private ShipmentTypeEnum type;;
    private String delivery;
    private @NotNull PriceResponse prices;

    public ShipmentProductResponse(Shipment shipment) {
        this.id = shipment.getId();
        this.description = shipment.getDescription();
        this.country = shipment.getCoverageArea().countryName();
        this.prices = getPricesDto(shipment);
        this.type = shipment.getType();
        this.delivery = getFormattedShipmentDelivery(shipment);
    }

    private String getFormattedShipmentDelivery(Shipment shipment) {
        return "Between " + shipment.getDeadline().minLimit() + " " + shipment.getDeadline().minFrequency() + " to "
                + shipment.getDeadline().maxLimit() + " " + shipment.getDeadline().maxFrequency();
    }

    private PriceResponse getPricesDto(Shipment shipment) {
        Double discountPercent = 0.0;
        Double currentPrice = shipment.getPrice();

        FormattedPrice before = new FormattedPrice(shipment.getPrice(),
                shipment.getCurrency() + " " + shipment.getPrice(), discountPercent);

        FormattedPrice current = new FormattedPrice(currentPrice,
                shipment.getCurrency() + " " + currentPrice, discountPercent);
        return PriceResponse.builder()
                .before(before)
                .current(current)
                .build();
    }
}
