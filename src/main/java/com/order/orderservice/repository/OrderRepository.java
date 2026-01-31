package com.order.orderservice.repository;

import com.order.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
//gives save(),findAll(),findById(),delete() Automatically.