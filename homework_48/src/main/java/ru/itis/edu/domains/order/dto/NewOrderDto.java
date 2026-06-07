package ru.itis.edu.domains.order.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class NewOrderDto {

    @NotBlank(message = "productName should not be blank")
    private String productName;

    @NotNull(message = "count should not be null")
    @Min(value = 1, message = "count should be greater than 0")
    private Integer count;

    @NotNull(message = "price should not be null")
    @DecimalMin(value = "0.01", message = "price should be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "deliveryAddress should not be blank")
    private String deliveryAddress;

    public NewOrderDto() {
    }

    public NewOrderDto(String productName, Integer count, BigDecimal price, String deliveryAddress) {
        this.productName = productName;
        this.count = count;
        this.price = price;
        this.deliveryAddress = deliveryAddress;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
