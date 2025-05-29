package com.fbr.ecommerce.controller.dto;

import com.fbr.ecommerce.entities.OrderEntity;
import com.fbr.ecommerce.entities.OrderItemEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(Long orderId,
                               BigDecimal total,
                               LocalDateTime orderDate,
                               UUID userId,
                               List<OrderItemResponseDto> items
                               ) {

    public static OrderResponseDto fromEntity(OrderEntity order){
        return new OrderResponseDto(
                order.getOrderId(),
                order.getTotal(),
                order.getOrderDate(),
                order.getUser().getId(),
                getItems(order.getItems())
        );
    }

    private static List<OrderItemResponseDto> getItems(List<OrderItemEntity> items) {
        return items
                .stream()
                .map(OrderItemResponseDto::fromEntity)
                .toList();
    }

}
