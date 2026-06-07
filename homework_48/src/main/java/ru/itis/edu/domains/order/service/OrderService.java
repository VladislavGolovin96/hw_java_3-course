package ru.itis.edu.domains.order.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.itis.edu.domains.order.dto.NewOrderDto;
import ru.itis.edu.domains.order.dto.OrderDto;
import ru.itis.edu.domains.order.entity.Order;
import ru.itis.edu.domains.order.exceptions.OrderNotFoundException;
import ru.itis.edu.domains.order.mapper.OrderMapper;
import ru.itis.edu.domains.order.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderDto createOrder(NewOrderDto newOrder) {
        Order order = new Order(newOrder.getProductName(),
                newOrder.getCount(),
                newOrder.getPrice(),
                newOrder.getDeliveryAddress());

        orderRepository.save(order);

        return orderMapper.from(order);
    }

    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(orderId));
        return orderMapper.from(order);
    }
}
