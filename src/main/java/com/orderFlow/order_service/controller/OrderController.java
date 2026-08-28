package com.orderFlow.order_service.controller;

import com.orderFlow.order_service.dto.CreateOrderRequest;
import com.orderFlow.order_service.entity.Order;
import com.orderFlow.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/health")
    public String health() {
        return "Order Service is Running";
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder( @Valid @RequestBody CreateOrderRequest request){
        return orderService.createOrder(request);
    }

    @GetMapping("/{orderId}")
    public Order getOrders(@Valid @PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
