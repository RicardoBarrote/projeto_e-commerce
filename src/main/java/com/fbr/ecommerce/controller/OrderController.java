package com.fbr.ecommerce.controller;

import com.fbr.ecommerce.controller.dto.*;
import com.fbr.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDto dto) {
        var order = orderService.createOrder(dto);

        return ResponseEntity.created(URI.create("/orders/" + order.getOrderId())).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<OrderSummaryDto>> listOrders(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var order = orderService.findAll(page, pageSize);

        return ResponseEntity.ok(new ApiResponse<>(
                order.getContent(), new PaginationResponseDto(page, pageSize, order.getTotalElements(), order.getSize())
        ));
    }

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable("orderId") Long orderId) {

        var order = orderService.findById(orderId);

        return order.isPresent()
                ? ResponseEntity.ok(OrderResponseDto.fromEntity(order.get()))
                : ResponseEntity.notFound().build();
    }
}
