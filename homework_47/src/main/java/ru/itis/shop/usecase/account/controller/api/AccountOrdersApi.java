package ru.itis.shop.usecase.account.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.shop.domains.orders.dto.NewOrderDto;
import ru.itis.shop.domains.orders.dto.OrderDto;

import java.util.List;

@RequestMapping("/api/v1/")
public interface AccountOrdersApi {

    @Operation(summary = "Получение списка заказов пользователя")
    @ApiResponses(value = {
            @ApiResponse(description = "Информация о заказах", responseCode = "200"),
            @ApiResponse(description = "Пользователь не найден", responseCode = "404")
    })
    @GetMapping("/accounts/{account-id}/orders")
    List<OrderDto> getAccountOrders(
            @Parameter(description = "Идентификатор пользователя") @PathVariable("account-id") Long accountId);

    @Operation(summary = "Добавление заказа пользователю")
    @ApiResponses(value = {
            @ApiResponse(description = "Информация о добавленном заказе", responseCode = "201"),
            @ApiResponse(description = "Пользователь не найден", responseCode = "404")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/accounts/{account-id}/orders")
    OrderDto addAccountOrder(
            @Parameter(description = "Идентификатор пользователя") @PathVariable("account-id") Long accountId,
            @RequestBody NewOrderDto newOrder);
}
