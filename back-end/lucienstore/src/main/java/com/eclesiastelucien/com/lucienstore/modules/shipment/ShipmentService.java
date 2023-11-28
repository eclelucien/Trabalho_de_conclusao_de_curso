package com.eclesiastelucien.com.lucienstore.modules.shipment;

import com.eclesiastelucien.com.lucienstore.modules.shipment.models.Shipment;

public interface ShipmentService {

    Iterable<Shipment> findAll(Long userId);

    Shipment findById(Long id);

    Shipment create(Shipment shipment);

    Shipment update(Long id, Shipment shipment);

    void delete(Long id);
}
