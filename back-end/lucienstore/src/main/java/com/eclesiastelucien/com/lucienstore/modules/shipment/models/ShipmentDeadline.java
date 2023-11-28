package com.eclesiastelucien.com.lucienstore.modules.shipment.models;

import java.io.Serializable;

import com.eclesiastelucien.com.lucienstore.modules.shipment.enums.ShipmentFrequencyEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ShipmentDeadline(@NotNull int minLimit,
                @NotNull int maxLimit,

                @NotNull @Enumerated(EnumType.STRING) ShipmentFrequencyEnum minFrequency,
                @NotNull @Enumerated(EnumType.STRING) ShipmentFrequencyEnum maxFrequency) implements Serializable {
}
