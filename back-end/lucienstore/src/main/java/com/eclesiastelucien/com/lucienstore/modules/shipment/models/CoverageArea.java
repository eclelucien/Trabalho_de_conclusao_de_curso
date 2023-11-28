package com.eclesiastelucien.com.lucienstore.modules.shipment.models;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CoverageArea(

                @NotNull String countryCode,
                @NotNull String countryName,

                String stateOrDepartment,
                String city,
                String neighborhood) implements Serializable {

}
