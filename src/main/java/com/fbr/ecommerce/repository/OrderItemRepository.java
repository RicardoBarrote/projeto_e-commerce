package com.fbr.ecommerce.repository;

import com.fbr.ecommerce.entities.OrderItemEntity;
import com.fbr.ecommerce.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemId> {
}
