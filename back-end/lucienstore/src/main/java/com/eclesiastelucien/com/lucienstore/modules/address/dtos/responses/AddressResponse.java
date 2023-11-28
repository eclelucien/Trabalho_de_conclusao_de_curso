package com.eclesiastelucien.com.lucienstore.modules.address.dtos.responses;

import com.eclesiastelucien.com.lucienstore.modules.address.enums.AddressTypeEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponse {
    private Long id;
    private String countryCode;
    private String countryName;
    private String stateOrDepartment;
    private String city;
    private String neighborhood;
    private String street;
    private int number;
    private boolean isDefault;
    private AddressTypeEnum type;
}