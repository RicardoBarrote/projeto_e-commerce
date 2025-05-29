package com.fbr.ecommerce.controller.dto;

import com.fbr.ecommerce.entities.OrderItemEntity;

import java.math.BigDecimal;

public record OrderItemResponseDto(BigDecimal salePrice,
                                   Integer quantity,
                                   ProductResponseDto product) {

    public static OrderItemResponseDto fromEntity(OrderItemEntity orderItemEntity) {
        return new OrderItemResponseDto(
                orderItemEntity.getSalePrice(),
                orderItemEntity.getQuantity(),
                ProductResponseDto.fromEntity(orderItemEntity.getId().getProduct())
        );
    }
}
