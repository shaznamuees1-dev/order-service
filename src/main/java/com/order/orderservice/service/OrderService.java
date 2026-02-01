package com.order.orderservice.service;

import com.order.orderservice.domain.Order;
import com.order.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import com.order.orderservice.dto.OrderRequestDTO;
import com.order.orderservice.dto.OrderResponseDTO;


import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
    Order order = new Order();
    order.setCustomerName(dto.getCustomerName());
    order.setTotalAmount(dto.getTotalAmount());
    order.setStatus(dto.getStatus());

    Order saved = orderRepository.save(order);

    OrderResponseDTO response = new OrderResponseDTO();
    response.setId(saved.getId());
    response.setCustomerName(saved.getCustomerName());
    response.setTotalAmount(saved.getTotalAmount());
    response.setStatus(saved.getStatus());
    response.setCreatedAt(saved.getCreatedAt());

    return response;
}


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
