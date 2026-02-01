package com.order.orderservice.service;

import com.order.orderservice.domain.Order;
import com.order.orderservice.dto.OrderRequestDTO;
import com.order.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
 
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldCreateOrder() {
        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setCustomerName("Mockito User");
        dto.setTotalAmount(1500.0);

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setCustomerName("Mockito User");
        savedOrder.setTotalAmount(1500.0);
        savedOrder.setStatus("CREATED");

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        var result = orderService.createOrder(dto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getCustomerName()).isEqualTo("Mockito User");
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void shouldUpdateOrderStatus() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus("CREATED");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        Order updated = orderService.updateOrderStatus(1L, "SHIPPED");

        assertThat(updated.getStatus()).isEqualTo("SHIPPED");
    }

    @Test
    void shouldFetchOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order()));

        List<Order> orders = orderService.getAllOrders();

        assertThat(orders).isNotEmpty();
        verify(orderRepository).findAll();
    }

    @Test
void shouldThrowExceptionWhenUpdatingNonExistingOrder() {
     
    Long invalidId = 999L;

    when(orderRepository.findById(invalidId))
            .thenReturn(Optional.empty());

    
    RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> orderService.updateOrderStatus(invalidId, "SHIPPED")
    );

    assertTrue(exception.getMessage().contains("Order not found"));

}

}
