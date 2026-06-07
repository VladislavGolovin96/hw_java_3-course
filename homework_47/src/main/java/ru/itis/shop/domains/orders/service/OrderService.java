package ru.itis.shop.domains.orders.service;

import org.springframework.stereotype.Service;
import ru.itis.shop.domains.accounts.entity.Account;
import ru.itis.shop.domains.orders.dto.NewOrderDto;
import ru.itis.shop.domains.orders.dto.OrderDto;
import ru.itis.shop.domains.orders.entity.Order;
import ru.itis.shop.domains.orders.repository.OrderRepository;

import static ru.itis.shop.domains.orders.dto.OrderDto.from;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto addOrder(NewOrderDto newOrder, Account owner) {
        Order order = new Order(LocalDate.parse(newOrder.getDate()), owner);

        orderRepository.save(order);

        return from(order);
    }

    public List<OrderDto> getAccountOrders(Long accountId) {
        return from(orderRepository.findByOwner_Id(accountId));
    }
}
