package ru.itis.edu.domains.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.edu.domains.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
