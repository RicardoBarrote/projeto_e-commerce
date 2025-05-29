package com.fbr.ecommerce.service;

import com.fbr.ecommerce.controller.dto.CreateOrderDto;
import com.fbr.ecommerce.controller.dto.OrderItemDto;
import com.fbr.ecommerce.controller.dto.OrderSummaryDto;
import com.fbr.ecommerce.entities.*;
import com.fbr.ecommerce.exception.CreateOrderException;
import com.fbr.ecommerce.repository.OrderRepository;
import com.fbr.ecommerce.repository.ProductRepository;
import com.fbr.ecommerce.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public OrderEntity createOrder(CreateOrderDto dto) {
        var order = new OrderEntity();

        var user = getUser(dto);
        var orderItems = validateOrderItems(order, dto);
        var total = calculateTotalValue(orderItems);

        order.setOrderDate(LocalDateTime.now());
        order.setTotal(total);
        order.setUser(user);
        order.setItems(orderItems);

        return orderRepository.save(order);
    }

    private BigDecimal calculateTotalValue(List<OrderItemEntity> orderItems) {
        return orderItems
                .stream()
                .map(i -> i.getSalePrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }

    private List<OrderItemEntity> validateOrderItems(OrderEntity order, CreateOrderDto dto) {
        if (dto.items().isEmpty()) {
            throw new CreateOrderException("Order items is empty");
        }

        return dto.items()
                .stream()
                .map(orderItemDto -> getOrderItem(order, orderItemDto))
                .toList();
    }

    private OrderItemEntity getOrderItem(OrderEntity order, OrderItemDto orderItemDto) {
        var orderItemEntity = new OrderItemEntity();
        var product = getProduct(orderItemDto.productId());
        var orderId = new OrderItemId();

        orderId.setOrder(order);
        orderId.setProduct(product);

        orderItemEntity.setId(orderId);
        orderItemEntity.setQuantity(orderItemDto.quantity());
        orderItemEntity.setSalePrice(product.getPrice());

        return orderItemEntity;
    }

    private ProductEntity getProduct(Long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new CreateOrderException("product not found"));
    }

    private UserEntity getUser(CreateOrderDto dto) {
        return userRepository
                .findById(dto.userId())
                .orElseThrow(() -> new CreateOrderException("userId not found"));
    }

    public Page<OrderSummaryDto> findAll(Integer page, Integer pageSize) {
        return orderRepository.findAll(PageRequest.of(page, pageSize))
                .map(entity ->
                        new OrderSummaryDto(
                                entity.getOrderId(),
                                entity.getOrderDate(),
                                entity.getUser().getId(),
                                entity.getTotal()
                        ));
    }

    public Optional<OrderEntity> findById(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
