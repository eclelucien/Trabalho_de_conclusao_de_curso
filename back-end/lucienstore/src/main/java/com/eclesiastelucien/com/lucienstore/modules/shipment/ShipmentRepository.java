package com.eclesiastelucien.com.lucienstore.modules.shipment;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.eclesiastelucien.com.lucienstore.modules.shipment.models.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Iterable<Shipment> findAllBySellerId(Long sellerId);

}
