package com.eclesiastelucien.com.lucienstore.modules.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eclesiastelucien.com.lucienstore.commons.exceptions.ResourceNotFoundException;
import com.eclesiastelucien.com.lucienstore.commons.utils.BaseServiceImpl;
import com.eclesiastelucien.com.lucienstore.modules.address.dtos.requests.AddressRequest;
import com.eclesiastelucien.com.lucienstore.modules.address.dtos.responses.AddressResponse;
import com.eclesiastelucien.com.lucienstore.modules.address.modules.Address;
import com.eclesiastelucien.com.lucienstore.modules.user.UserRepository;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl extends BaseServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public List<AddressResponse> getAllAddress() {

        Long userId = this.authenticatedUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        List<Address> addresses = user.getAddresses();
        return addresses.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public AddressResponse getFirstAddress() {

        Long userId = this.authenticatedUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        List<Address> addresses = user.getAddresses();

        List<AddressResponse> add = addresses.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        if (add.isEmpty()) {
            throw new ResourceNotFoundException("No address found");
        }
        return add.get(0);
    }

    public AddressResponse getAddressById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.map(this::convertToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + id));
    }

    public AddressResponse createAddress(AddressRequest addressDTO) {
        Long userId = this.authenticatedUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        Address address = convertToEntity(addressDTO);
        address.setUser(user);

        Address createdAddress = addressRepository.save(address);

        return convertToResponse(createdAddress);
    }

    public AddressResponse updateAddress(Long id, AddressRequest addressDTO) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.map(address -> {
            Address updatedAddress = convertToEntity(addressDTO);
            updatedAddress.setId(address.getId());
            Address savedAddress = addressRepository.save(updatedAddress);
            return convertToResponse(savedAddress);
        }).orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + id));
    }

    public boolean deleteAddress(Long id) {
        try {
            Optional<Address> optionalAddress = addressRepository.findById(id);
            if (optionalAddress.isPresent()) {
                addressRepository.deleteById(id);
                return true;
            } else {
                throw new ResourceNotFoundException("Address not found with ID: " + id);
            }
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Address not found with ID: " + id);
        }
    }

    private AddressResponse convertToResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .countryCode(address.getCountryCode())
                .countryName(address.getCountryName())
                .stateOrDepartment(address.getStateOrDepartment())
                .city(address.getCity())
                .neighborhood(address.getNeighborhood())
                .street(address.getStreet())
                .number(address.getNumber())
                .isDefault(address.isDefault())
                .type(address.getType())
                .build();
    }

    private Address convertToEntity(AddressRequest addressDTO) {
        return Address.builder()
                .countryCode(addressDTO.getCountryCode())
                .countryName(addressDTO.getCountryName())
                .stateOrDepartment(addressDTO.getStateOrDepartment())
                .city(addressDTO.getCity())
                .neighborhood(addressDTO.getNeighborhood())
                .street(addressDTO.getStreet())
                .number(addressDTO.getNumber())
                .isDefault(addressDTO.isDefault())
                .type(addressDTO.getType())
                .build();
    }
}
