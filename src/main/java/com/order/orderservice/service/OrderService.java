package com.order.orderservice.service;

import com.order.orderservice.domain.Order;
import com.order.orderservice.dto.OrderRequestDTO;
import com.order.orderservice.dto.OrderResponseDTO;
import com.order.orderservice.dto.OrderUpdateDTO;
import com.order.orderservice.exception.OrderNotFoundException;
import com.order.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Service
public class OrderService {

     private static final Logger logger =
            LoggerFactory.getLogger(OrderService.class);


    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    
    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));

        order.setStatus(status);
        return orderRepository.save(order);
    }

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {

    logger.info("Creating new order for customer: {}", dto.getCustomerName());

    Order order = new Order();
    order.setCustomerName(dto.getCustomerName());
    order.setTotalAmount(dto.getTotalAmount());
    order.setStatus("CREATED"); 
     
    Order saved = orderRepository.save(order);

    logger.info("Order created successfully with id: {}", saved.getId());

    OrderResponseDTO response = new OrderResponseDTO();
    response.setId(saved.getId());
    response.setCustomerName(saved.getCustomerName());
    response.setTotalAmount(saved.getTotalAmount());
    response.setStatus(saved.getStatus());
    response.setCreatedAt(saved.getCreatedAt());

    return response;
}

public Order getOrderById(Long id) {
    return orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(
                    "Order not found with id " + id));
}

public void deleteOrder(Long id) {

    logger.info("Attempting to delete order with id {}", id);

    Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(
                    "Order not found with id " + id));

    orderRepository.delete(order);

    logger.info("Order {} deleted successfully", id);
}


public Order updateOrder(Long id, OrderUpdateDTO dto) {

    logger.info("Updating order {} with new values", id);

    Order order = orderRepository.findById(id)
            .orElseThrow(() ->
                    new OrderNotFoundException("Order not found with id " + id)
            );

    order.setCustomerName(dto.getCustomerName());
    order.setTotalAmount(dto.getTotalAmount());
    order.setStatus(dto.getStatus());

    Order updated = orderRepository.save(order);

    logger.info("Order {} updated successfully", id);

    return updated;
}



}
