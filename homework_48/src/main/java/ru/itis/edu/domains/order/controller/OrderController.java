package ru.itis.edu.domains.order.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.edu.domains.order.dto.NewOrderDto;
import ru.itis.edu.domains.order.dto.OrderDto;
import ru.itis.edu.domains.order.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid NewOrderDto newOrder) {
        return orderService.createOrder(newOrder);
    }

    @GetMapping("/{order-id}")
    public OrderDto getOrder(@PathVariable("order-id") Long orderId) {
        return orderService.getOrder(orderId);
    }
}
