package com.order.orderservice.controller;

import com.order.orderservice.domain.Order;
import com.order.orderservice.dto.OrderRequestDTO;
import com.order.orderservice.dto.OrderResponseDTO;
import com.order.orderservice.service.OrderService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDTO createOrder(@Valid @RequestBody OrderRequestDTO dto) {
    return orderService.createOrder(dto);

}


    // Get All Orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
