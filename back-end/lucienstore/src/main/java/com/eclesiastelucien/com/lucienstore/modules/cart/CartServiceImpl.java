package com.eclesiastelucien.com.lucienstore.modules.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eclesiastelucien.com.lucienstore.commons.exceptions.ResourceNotFoundException;
import com.eclesiastelucien.com.lucienstore.commons.utils.BaseServiceImpl;
import com.eclesiastelucien.com.lucienstore.commons.utils.Utils;
import com.eclesiastelucien.com.lucienstore.modules.cart.dtos.requests.CartRequest;
import com.eclesiastelucien.com.lucienstore.modules.cart.dtos.responses.CartResponse;
import com.eclesiastelucien.com.lucienstore.modules.cart.models.CartCost;
import com.eclesiastelucien.com.lucienstore.modules.cart.models.SubTotal;
import com.eclesiastelucien.com.lucienstore.modules.order.OrderRepository;
import com.eclesiastelucien.com.lucienstore.modules.order.enums.OrderStatus;
import com.eclesiastelucien.com.lucienstore.modules.order.enums.OrderStatusEnum;
import com.eclesiastelucien.com.lucienstore.modules.order.models.Order;
import com.eclesiastelucien.com.lucienstore.modules.order.models.OrderItem;
import com.eclesiastelucien.com.lucienstore.modules.order.orderItem.OrderItemRepository;
import com.eclesiastelucien.com.lucienstore.modules.product.ProductService;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Product;
import com.eclesiastelucien.com.lucienstore.modules.shipment.ShipmentServiceImpl;
import com.eclesiastelucien.com.lucienstore.modules.shipment.models.Shipment;
import com.eclesiastelucien.com.lucienstore.modules.user.UserRepository;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CartServiceImpl extends BaseServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private ShipmentServiceImpl shipmentServiceImpl;

    @Autowired
    private OrderRepository orderRepository;

    public void addToCart(CartRequest addToCartDto) {

        Long userId = this.authenticatedUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        Product product = productServiceImpl.findById(addToCartDto.getProductId());
        shipmentServiceImpl.findById(addToCartDto.getShipmentId());

        List<Order> cartOrders = cartRepository.findAllByBuyerAndStatus(user, OrderStatusEnum.IN_CART);
        OrderItem orderItem;
        Order cartOrder;
        if (cartOrders.isEmpty()) {
            cartOrder = new Order();
            cartOrder.setCreatedAt(LocalDateTime.now());
            cartOrder.setStatus(OrderStatus.IN_CART);
            cartOrder.setBuyer(user);
            cartOrder = orderRepository.save(cartOrder);
        } else {
            cartOrder = cartOrders.get(0);
        }

        Optional<OrderItem> existingItemOptional = orderItemRepository.findByOrderAndProduct(cartOrder, product);

        if (existingItemOptional.isPresent()) {
            orderItem = existingItemOptional.get();
            orderItem.setQuantity(orderItem.getQuantity() + addToCartDto.getQuantity());
        } else {
            orderItem = new OrderItem(cartOrder, product, addToCartDto.getQuantity(), product.getPrice(),
                    addToCartDto.getShipmentId(), addToCartDto.getProperties());
        }
        orderItemRepository.save(orderItem);
    }

    public CartCost listCartItems() {
        Long userId = this.authenticatedUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);
        CartCost cartCost = new CartCost();
        List<Order> cartOrders = cartRepository.findAllByBuyerAndStatus(user, OrderStatusEnum.IN_CART);

        List<CartResponse> cartItems = new ArrayList<>();
        double productSubtotal = 0.0;
        double shipmentSubtotal = 0.0;

        if (!cartOrders.isEmpty()) {
            Order cartOrder = cartOrders.get(0);
            if (!cartOrder.getItems().isEmpty()) {
                for (OrderItem orderItem : cartOrder.getItems()) {
                    CartResponse cartDto = new CartResponse(orderItem, shipmentServiceImpl);
                    cartItems.add(cartDto);

                    double itemProductSubtotal = orderItem.getSubTotal();
                    productSubtotal += itemProductSubtotal;
                    Shipment shipment = shipmentServiceImpl.findById(orderItem.getShipmentId());
                    double itemShipmentSubtotal = shipment.getPrice();
                    shipmentSubtotal += itemShipmentSubtotal;
                }
            }
        }
        String formattedProductSubtotal = "US$ " + Utils.formatDecimal(productSubtotal);
        String formattedShipmentSubtotal = "US$ " + Utils.formatDecimal(shipmentSubtotal);
        double total = productSubtotal + shipmentSubtotal;

        cartCost.setCount(cartItems.size());
        cartCost.setProductSubtotal(new SubTotal(Utils.formatDecimal(productSubtotal), formattedProductSubtotal));
        cartCost.setShipmentSubtotal(new SubTotal(shipmentSubtotal, formattedShipmentSubtotal));
        cartCost.setTotalCost(Utils.formatDecimal(total));

        cartCost.setCartItems(cartItems);
        return cartCost;
    }

    public void updateCartItem(int quantity, Long itemId) {
        Long userId = this.authenticatedUser().getId();
        Product product = productServiceImpl.findById(itemId);
        User user = userRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        List<Order> cartOrders = cartRepository.findAllByBuyerAndStatus(user, OrderStatusEnum.IN_CART);

        if (!cartOrders.isEmpty()) {
            Order cartOrder = cartOrders.get(0);
            Optional<OrderItem> existingItemOptional = orderItemRepository.findByOrderAndProduct(cartOrder, product);
            if (existingItemOptional.isPresent()) {
                OrderItem orderItem = existingItemOptional.get();
                orderItem.setQuantity(quantity);
                orderItemRepository.save(orderItem);
                return;
            }
        }
        throw new ResourceNotFoundException("Product not found");
    }

    public void deleteCartItem(Long itemId) {
        Long userId = this.authenticatedUser().getId();
        Product product = productServiceImpl.findById(itemId);
        User user = userRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);
        List<Order> cartOrders = cartRepository.findAllByBuyerAndStatus(user, OrderStatusEnum.IN_CART);

        if (!cartOrders.isEmpty()) {
            Order cartOrder = cartOrders.get(0);
            Optional<OrderItem> existingItemOptional = orderItemRepository.findByOrderAndProduct(cartOrder, product);
            if (existingItemOptional.isPresent()) {
                OrderItem orderItem = existingItemOptional.get();
                orderItemRepository.delete(orderItem);
                return;
            }
        }
        throw new ResourceNotFoundException("Product not found");
    }

}
