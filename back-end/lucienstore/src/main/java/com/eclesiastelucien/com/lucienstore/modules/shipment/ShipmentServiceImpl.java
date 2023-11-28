package com.eclesiastelucien.com.lucienstore.modules.shipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eclesiastelucien.com.lucienstore.commons.exceptions.ResourceNotFoundException;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.Shipment;

import java.util.Optional;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Iterable<Shipment> findAll(Long userId) {
        return shipmentRepository.findAllBySellerId(userId);
    }

    public Shipment findById(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with id " + id + " was not found."));
    }

    public Shipment create(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment update(Long id, Shipment shipment) throws ResourceNotFoundException {
        Optional<Shipment> optionalShipment = shipmentRepository.findById(id);
        if (optionalShipment.isPresent()) {
            Shipment existingShipment = optionalShipment.get();
            existingShipment.setDescription(shipment.getDescription());
            existingShipment.setPrice(shipment.getPrice());
            existingShipment.setType(shipment.getType());
            existingShipment.setSeller(shipment.getSeller());
            existingShipment.setCurrency(shipment.getCurrency());
            existingShipment.setDiscount(shipment.getDiscount());
            existingShipment.setDeadline(shipment.getDeadline());
            existingShipment.setCoverageArea(shipment.getCoverageArea());
            existingShipment.setProducts(shipment.getProducts());

            return shipmentRepository.save(existingShipment);
        } else {
            throw new ResourceNotFoundException("Shipment with id " + id + " was not found.");
        }
    }

    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Shipment> optionalShipment = shipmentRepository.findById(id);
        if (optionalShipment.isPresent()) {
            shipmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Shipment with id " + id + " was not found.");
        }
    }
}
