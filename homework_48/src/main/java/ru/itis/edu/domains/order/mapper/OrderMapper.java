package ru.itis.edu.domains.order.mapper;

import org.springframework.stereotype.Component;
import ru.itis.edu.domains.order.dto.OrderDto;
import ru.itis.edu.domains.order.entity.Order;

@Component
public class OrderMapper {

    public OrderDto from(Order order) {
        return new OrderDto(order.getId(),
                order.getProductName(),
                order.getCount(),
                order.getPrice(),
                order.getDeliveryAddress());
    }
}
