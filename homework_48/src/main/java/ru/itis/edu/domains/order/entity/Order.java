package ru.itis.edu.domains.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer count;
    private BigDecimal price;
    private String deliveryAddress;

    public Order() {
    }

    public Order(Long id, String productName, Integer count, BigDecimal price, String deliveryAddress) {
        this.id = id;
        this.productName = productName;
        this.count = count;
        this.price = price;
        this.deliveryAddress = deliveryAddress;
    }

    public Order(String productName, Integer count, BigDecimal price, String deliveryAddress) {
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
