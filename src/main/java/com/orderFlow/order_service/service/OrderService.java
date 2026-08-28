package com.orderFlow.order_service.service;

import com.orderFlow.order_service.dto.CreateOrderRequest;
import com.orderFlow.order_service.entity.Order;
import com.orderFlow.order_service.enums.OrderStatus;
import com.orderFlow.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setAmount(request.getAmount());

        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}



