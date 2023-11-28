package com.eclesiastelucien.com.lucienstore.modules.shipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eclesiastelucien.com.lucienstore.commons.dtos.ApiResponse;
import com.eclesiastelucien.com.lucienstore.modules.shipment.dtos.requests.ShipmentRequest;
import com.eclesiastelucien.com.lucienstore.modules.shipment.dtos.responses.ShipmentResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Tag(name = "shipments", description = "CRUD REST APIs - Create Shipment, Update Shipment, Get User, Get All shipments, Delete Shipment")
@RestController
@RequestMapping("api/v1/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentServiceImpl shipmentServiceImpl;

    @GetMapping
    public ResponseEntity<List<ShipmentResponse>> findAll(@Min(1) Long userId) {
        List<ShipmentResponse> shipments = StreamSupport
                .stream(shipmentServiceImpl.findAll(userId).spliterator(), false)
                .map(ShipmentResponse::fromShipment)
                .collect(Collectors.toList());

        return ResponseEntity.ok(shipments);
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<ShipmentResponse> findById(@PathVariable @Min(1) Long shipmentId) {
        ShipmentResponse shipmentResponse = ShipmentResponse.fromShipment(shipmentServiceImpl.findById(shipmentId));
        return ResponseEntity.ok(shipmentResponse);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(@RequestBody @Valid ShipmentRequest shipmentRequest) {
        ShipmentResponse createdShipment = ShipmentResponse
                .fromShipment(shipmentServiceImpl.create(shipmentRequest.toShipment()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShipment);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{shipmentId}")
    public ResponseEntity<ShipmentResponse> updateShipment(@PathVariable @Min(1) Long shipmentId,
            @RequestBody ShipmentRequest shipmentRequest) {
        ShipmentResponse updatedShipment = ShipmentResponse
                .fromShipment(shipmentServiceImpl.update(shipmentId, shipmentRequest.toShipment()));

        return ResponseEntity.ok(updatedShipment);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{shipmentId}")
    public ResponseEntity<ApiResponse> deleteShipment(@PathVariable @Min(1) Long shipmentId) {
        shipmentServiceImpl.delete(shipmentId);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Shipment has been deleted"), HttpStatus.OK);
    }
}
