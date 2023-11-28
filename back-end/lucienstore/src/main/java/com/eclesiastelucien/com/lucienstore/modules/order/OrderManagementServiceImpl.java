package com.eclesiastelucien.com.lucienstore.modules.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eclesiastelucien.com.lucienstore.commons.exceptions.ForbiddenResourceException;
import com.eclesiastelucien.com.lucienstore.commons.exceptions.InvalidFieldException;
import com.eclesiastelucien.com.lucienstore.commons.exceptions.ResourceNotFoundException;
import com.eclesiastelucien.com.lucienstore.commons.utils.BaseServiceImpl;
import com.eclesiastelucien.com.lucienstore.commons.utils.Utils;
import com.eclesiastelucien.com.lucienstore.modules.cart.CartServiceImpl;
import com.eclesiastelucien.com.lucienstore.modules.cart.models.CartCost;
import com.eclesiastelucien.com.lucienstore.modules.order.dtos.OrderResponse;
import com.eclesiastelucien.com.lucienstore.modules.order.enums.OrderItemStatus;
import com.eclesiastelucien.com.lucienstore.modules.order.enums.OrderStatus;
import com.eclesiastelucien.com.lucienstore.modules.order.models.Order;
import com.eclesiastelucien.com.lucienstore.modules.order.models.OrderItem;
import com.eclesiastelucien.com.lucienstore.modules.order.orderItem.OrderItemRepository;
import com.eclesiastelucien.com.lucienstore.modules.order.orderItem.enums.OrderItemStatusEnum;
import com.eclesiastelucien.com.lucienstore.modules.order.orderItem.responses.OrderItemResponse;
import com.eclesiastelucien.com.lucienstore.modules.product.ProductService;
import com.eclesiastelucien.com.lucienstore.modules.product.dtos.ProductDetailResponse;
import com.eclesiastelucien.com.lucienstore.modules.product.models.Product;
import com.eclesiastelucien.com.lucienstore.modules.user.enums.UserRoleEnum;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

import jakarta.transaction.Transactional;

@Service
public class OrderManagementServiceImpl extends BaseServiceImpl implements OrderManagementService {

    private static final Logger logger = LoggerFactory.getLogger(OrderManagementServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Override
    public List<OrderResponse> findAll() {
        User user = this.authenticatedUser();
        List<Order> orders = user.getOrders();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setId(order.getId());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setBuyer(order.getBuyer());

            List<OrderItemResponse> itemResponses = new ArrayList<>();
            for (OrderItem item : order.getItems()) {
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setProduct(new ProductDetailResponse(item.getProduct()));
                itemResponse.setQuantity(item.getQuantity());
                itemResponse.setPrice(item.getPrice());
                itemResponse.setSubTotal(item.getSubTotal());

                itemResponses.add(itemResponse);
            }
            orderResponse.setItems(itemResponses);

            orderResponse.setTotal(Utils.formatDecimal(order.getTotal()));

            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    @Override
    public OrderResponse findById(Long id) throws ResourceNotFoundException {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " was not found."));
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setBuyer(order.getBuyer());

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setProduct(new ProductDetailResponse(item.getProduct()));
            itemResponse.setQuantity(item.getQuantity());
            itemResponse.setPrice(item.getPrice());
            itemResponse.setSubTotal(item.getSubTotal());

            itemResponses.add(itemResponse);
        }
        orderResponse.setItems(itemResponses);

        orderResponse.setTotal(Utils.formatDecimal(order.getTotal()));
        return orderResponse;
    }

    @Override
    public List<OrderResponse> findByStatus(String newStatus) {
        List<Order> orders = orderRepository.findByStatus(newStatus);
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setId(order.getId());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setBuyer(order.getBuyer());

            List<OrderItemResponse> itemResponses = new ArrayList<>();
            for (OrderItem item : order.getItems()) {
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setProduct(new ProductDetailResponse(item.getProduct()));
                itemResponse.setQuantity(item.getQuantity());
                itemResponse.setPrice(item.getPrice());
                itemResponse.setSubTotal(item.getSubTotal());

                itemResponses.add(itemResponse);
            }
            orderResponse.setItems(itemResponses);

            orderResponse.setTotal(Utils.formatDecimal(order.getTotal()));

            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    @Override
    public List<OrderResponse> findByDate(LocalDateTime dateTime) {
        List<Order> orders = orderRepository.findByDate(dateTime);
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setId(order.getId());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setBuyer(order.getBuyer());

            List<OrderItemResponse> itemResponses = new ArrayList<>();
            for (OrderItem item : order.getItems()) {
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setProduct(new ProductDetailResponse(item.getProduct()));
                itemResponse.setQuantity(item.getQuantity());
                itemResponse.setPrice(item.getPrice());
                itemResponse.setSubTotal(item.getSubTotal());

                itemResponses.add(itemResponse);
            }
            orderResponse.setItems(itemResponses);

            orderResponse.setTotal(Utils.formatDecimal(order.getTotal()));

            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    @Override
    public List<OrderResponse> findByBuyerOrSeller(User user) {
        List<Order> orders = orderRepository.findByBuyerOrSeller(user.getId());
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setId(order.getId());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setBuyer(order.getBuyer());

            List<OrderItemResponse> itemResponses = new ArrayList<>();
            for (OrderItem item : order.getItems()) {
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setProduct(new ProductDetailResponse(item.getProduct()));
                itemResponse.setQuantity(item.getQuantity());
                itemResponse.setPrice(item.getPrice());
                itemResponse.setSubTotal(item.getSubTotal());

                itemResponses.add(itemResponse);
            }
            orderResponse.setItems(itemResponses);

            orderResponse.setTotal(Utils.formatDecimal(order.getTotal()));

            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    private void updateOrderStatus(Order order) {
        boolean allShipped = order.getItems().stream()
                .allMatch(item -> item.getStatus() == OrderItemStatus.SHIPPED);
        boolean allDelivered = order.getItems().stream()
                .allMatch(item -> item.getStatus() == OrderItemStatus.DELIVERED);
        boolean allProcessing = order.getItems().stream()
                .allMatch(item -> item.getStatus() == OrderItemStatus.PROCESSING);
        boolean allCancelled = order.getItems().stream()
                .allMatch(item -> item.getStatus() == OrderItemStatus.CANCELLED);
        if (allShipped) {
            order.setStatus(OrderStatus.SHIPPED);
        } else if (allDelivered) {
            order.setStatus(OrderStatus.DELIVERED);
        } else if (allProcessing) {
            order.setStatus(OrderStatus.PROCESSING);
        } else if (allCancelled) {
            order.setStatus(OrderStatus.CANCELLED);
        }
        orderRepository.save(order);
    }

    public OrderItem updateOrderItemStatusEnum(Long productId, Long orderId, OrderItemStatus newStatus) {
        User connectedUser = this.authenticatedUser();

        List<OrderItemStatusEnum> unauthorizedStatuses = List.of(OrderItemStatusEnum.CANCELLED,
                OrderItemStatusEnum.DELIVERED);

        if (connectedUser.getRole().equals(UserRoleEnum.ADMINISTRATOR)) {
            Product product = productServiceImpl.findById(productId);
            Optional<Order> orderOptional = this.orderRepository.findById(orderId);

            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();

                OrderItem orderItem = orderItemRepository.findByOrderAndProduct(order, product)
                        .orElseThrow(() -> new ResourceNotFoundException("Order not found with the informed ID."));

                if (unauthorizedStatuses.contains(orderItem.getStatus())
                        || orderItem.getStatus().equals(OrderItemStatusEnum.PAID)) {
                    throw new InvalidFieldException("Invalid status provided.");
                }

                if (orderItem.getStatus().equals(OrderItemStatusEnum.DELIVERED)) {
                    // Transfer transfer = this.stripeConnectProvider.createTransfer(orderItem,
                    // order.getPaymentIntent());
                }

                orderItem.setStatus(newStatus);
                orderItemRepository.save(orderItem);
                // we can send notification to the buyer in this part:
                // this.notificateBuyer(orderItem);

                updateOrderStatus(orderItem.getOrder());
                return orderItem;
            }
        }
        throw new ForbiddenResourceException();
    }

    @Override
    public void remove(Long id) {
        User connetedUser = this.authenticatedUser();
        if (connetedUser.getRole().equals(UserRoleEnum.ADMINISTRATOR)) {
            try {
                Optional<Order> orderOptional = this.orderRepository.findById(id);

                if (orderOptional.isPresent()) {
                    Order order = orderOptional.get();
                    // Before removing an order, we can want handle any related cleanup tasks
                    // For example, we can cancel any related payments or transfers
                    orderRepository.delete(order);
                }
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        }
        throw new ForbiddenResourceException();
    }

    // private void notificateSellers(Order order) {
    // for (OrderItem orderItem : order.getItems()) {
    // Product product = orderItem.getProduct();
    // Long userId = orderItem.getProduct().getSeller().getId();
    //
    // String message = "Your product " + product.getName() + " was purchased by
    // someone.";
    // this.notificationServiceImpl.createNotification(userId, message);
    // }
    // }

    // private void notificateBuyer(OrderItem orderItem) {
    // Product product = orderItem.getProduct();
    // Long userId = orderItem.getOrder().getBuyer().getId();

    // String message = "The status of your product " + product.getName() + " has
    // changed to " + orderItem.getStatus();
    // this.notificationServiceImpl.createNotification(userId, message);
    // }
}
