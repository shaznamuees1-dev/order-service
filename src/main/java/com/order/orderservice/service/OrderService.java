package com.order.orderservice.service;

import com.order.orderservice.domain.Order;
import com.order.orderservice.exception.OrderNotFoundException;
import com.order.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // CREATE ORDER (Entity-based)
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // GET ALL ORDERS
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // UPDATE STATUS
    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));

        order.setStatus(status);
        return orderRepository.save(order);
    }
}
