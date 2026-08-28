package com.orderFlow.order_service.service;

import com.orderFlow.order_service.dto.CreateOrderRequest;
import com.orderFlow.order_service.entity.Order;
import com.orderFlow.order_service.enums.OrderStatus;
import com.orderFlow.order_service.event.OrderCreatedEvent;
import com.orderFlow.order_service.kafka.OrderKafkaProducer;
import com.orderFlow.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderKafkaProducer orderKafkaProducer;

    public OrderService(OrderRepository orderRepository, OrderKafkaProducer orderKafkaProducer) {
        this.orderRepository = orderRepository;
        this.orderKafkaProducer = orderKafkaProducer;
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

        Order savedOrder = orderRepository.save(order);
//        return orderRepository.save(order);
//        create an OrderCreatedEvent
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getCustomerId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getAmount(),
                savedOrder.getStatus(),
                savedOrder.getCreatedAt()
        );
        //publish the event to Kafka
        orderKafkaProducer.publishOrderCreated(event);
        //return the saved order
        return savedOrder;

    }
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}



