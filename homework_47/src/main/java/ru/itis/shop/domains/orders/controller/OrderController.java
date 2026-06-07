package ru.itis.shop.domains.orders.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.shop.domains.orders.controller.api.OrderApi;
import ru.itis.shop.domains.orders.dto.NewOrderDto;
import ru.itis.shop.domains.orders.dto.OrderDto;
import ru.itis.shop.usecase.account.AccountOrderService;

@RestController
public class OrderController implements OrderApi {

    private final AccountOrderService accountOrderService;

    public OrderController(AccountOrderService accountOrderService) {
        this.accountOrderService = accountOrderService;
    }

    @Override
    public OrderDto addOrder(NewOrderDto newOrder) {
        return accountOrderService.addAccountOrder(newOrder.getAccountId(), newOrder);
    }
}
