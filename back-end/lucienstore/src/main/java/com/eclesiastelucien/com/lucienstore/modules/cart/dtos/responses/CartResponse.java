package com.eclesiastelucien.com.lucienstore.modules.cart.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.Property;

import com.eclesiastelucien.com.lucienstore.commons.utils.Utils;
import com.eclesiastelucien.com.lucienstore.modules.cart.models.SubTotal;
import com.eclesiastelucien.com.lucienstore.modules.order.models.Order;
import com.eclesiastelucien.com.lucienstore.modules.order.orderItem.models.OrderItem;
import com.eclesiastelucien.com.lucienstore.modules.shipment.ShipmentServiceImpl;
import com.eclesiastelucien.com.lucienstore.modules.shipment.dtos.responses.ShipmentResponse;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    private Integer quantity;
    private ProductCartResponse product;
    private List<Property> properties;
    private ShipmentResponse shipment;
    private SubTotal subtotal;
    private Order order;

    public CartResponse(OrderItem orderItem, ShipmentServiceImpl shipmentServiceImpl) {
        this.quantity = orderItem.getQuantity();
        this.product = new ProductCartResponse(orderItem.getProduct());
        this.subtotal = new SubTotal(Utils.formatDecimal(orderItem.getSubTotal()), "US$");
        this.properties = orderItem.getProperties();
        this.shipment = ShipmentResponse.fromShipment(shipmentServiceImpl.findById(orderItem.getShipmentId()));
        this.order = orderItem.getOrder();
    }
}
