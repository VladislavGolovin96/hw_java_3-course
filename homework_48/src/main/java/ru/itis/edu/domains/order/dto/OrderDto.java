package ru.itis.edu.domains.order.dto;

import java.math.BigDecimal;

public class OrderDto {

    private Long id;
    private String productName;
    private Integer count;
    private BigDecimal price;
    private String deliveryAddress;

    public OrderDto(Long id, String productName, Integer count, BigDecimal price, String deliveryAddress) {
        this.id = id;
        this.productName = productName;
        this.count = count;
        this.price = price;
        this.deliveryAddress = deliveryAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
