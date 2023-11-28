package com.eclesiastelucien.com.lucienstore.modules.address;

import java.util.List;

import com.eclesiastelucien.com.lucienstore.modules.address.dtos.requests.AddressRequest;
import com.eclesiastelucien.com.lucienstore.modules.address.dtos.responses.AddressResponse;

public interface AddressService {
    List<AddressResponse> getAllAddress();

    AddressResponse getAddressById(Long id);

    AddressResponse createAddress(AddressRequest addressDTO);

    AddressResponse updateAddress(Long id, AddressRequest addressDTO);

    boolean deleteAddress(Long id);
}