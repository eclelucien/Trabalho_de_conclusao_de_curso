package com.eclesiastelucien.com.lucienstore.modules.address;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.eclesiastelucien.com.lucienstore.commons.exceptions.InvalidFieldException;
import com.eclesiastelucien.com.lucienstore.commons.utils.Utils;
import com.eclesiastelucien.com.lucienstore.modules.address.dtos.requests.AddressRequest;
import com.eclesiastelucien.com.lucienstore.modules.address.dtos.responses.AddressResponse;

;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "addresses", description = "CRUD REST APIs - Create address, Update address, Get address, Get All address, Delete address")
@RestController
@RequestMapping("api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @GetMapping
    public AddressResponse getFirstAddress() {
        return addressServiceImpl.getFirstAddress();
    }

    @GetMapping("/{id}")
    public AddressResponse getAddressById(@PathVariable @Min(1) Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(Utils.getErrorMessage(bindingResult.getFieldErrors()));
        }
        return addressServiceImpl.getAddressById(id);
    }

    @PostMapping
    public AddressResponse registerAddress(@RequestBody @Valid AddressRequest addressRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(Utils.getErrorMessage(bindingResult.getFieldErrors()));
        }
        return addressServiceImpl.createAddress(addressRequest);
    }

    @PutMapping("/{id}")
    public AddressResponse updateAddress(
            @PathVariable @Min(1) Long id,
            @RequestBody @Valid AddressRequest addressRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(Utils.getErrorMessage(bindingResult.getFieldErrors()));
        }
        return addressServiceImpl.updateAddress(id, addressRequest);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAddress(@PathVariable @Min(1) Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFieldException(Utils.getErrorMessage(bindingResult.getFieldErrors()));
        }
        return addressServiceImpl.deleteAddress(id);
    }
}
