package ru.itis.shop.usecase.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.shop.domains.accounts.entity.Account;
import ru.itis.shop.domains.accounts.service.AccountService;
import ru.itis.shop.domains.orders.dto.NewOrderDto;
import ru.itis.shop.domains.orders.dto.OrderDto;
import ru.itis.shop.domains.orders.service.OrderService;

import java.util.List;

@Service
public class AccountOrderService {

    private final AccountService accountService;
    private final OrderService orderService;

    public AccountOrderService(AccountService accountService, OrderService orderService) {
        this.accountService = accountService;
        this.orderService = orderService;
    }

    public List<OrderDto> getAccountOrders(Long accountId) {
        accountService.checkAccountExists(accountId);
        return orderService.getAccountOrders(accountId);
    }

    @Transactional
    public OrderDto addAccountOrder(Long accountId, NewOrderDto newOrder) {
        Account owner = accountService.getAccountEntity(accountId);
        return orderService.addOrder(newOrder, owner);
    }
}
