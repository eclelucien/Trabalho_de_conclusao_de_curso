package com.eclesiastelucien.com.lucienstore.modules.address.dtos.requests;

import java.io.Serializable;

import com.eclesiastelucien.com.lucienstore.modules.address.enums.AddressTypeEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest implements Serializable {
    @NotNull(message = "countryCode cannot be null")
    private String countryCode;

    @NotNull(message = "countryName cannot be null")
    private String countryName;

    @NotNull(message = "stateOrDepartment cannot be null")
    private String stateOrDepartment;

    @NotNull(message = "city cannot be null")
    private String city;

    @NotNull(message = "neighborhood cannot be null")
    private String neighborhood;

    @NotNull(message = "street cannot be null")
    private String street;

    @NotNull(message = "number cannot be null")
    private int number;

    @NotNull(message = "isDefault cannot be null")
    private boolean isDefault;
    @NotNull(message = "type cannot be null")
    private AddressTypeEnum type;
}
