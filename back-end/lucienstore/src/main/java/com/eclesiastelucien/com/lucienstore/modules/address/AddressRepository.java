package com.eclesiastelucien.com.lucienstore.modules.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eclesiastelucien.com.lucienstore.modules.address.modules.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}