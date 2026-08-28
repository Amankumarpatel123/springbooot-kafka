package com.orderFlow.order_service.entity;

import com.orderFlow.order_service.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long CustomerId;

    @Column(nullable = false)
    private Long ProductId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal Amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column (nullable = false)
    private LocalDateTime createdAt;

    public Order() {
    }

    public long getId(){
       return id;
    }

    public long getCustomerId(){
        return CustomerId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", CustomerId=" + CustomerId +
                ", ProductId=" + ProductId +
                ", quantity=" + quantity +
                ", Amount=" + Amount +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
