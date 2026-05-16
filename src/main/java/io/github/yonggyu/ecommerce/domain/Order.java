package io.github.yonggyu.ecommerce.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private final Long id;
    private final Long userId;
    private final Long productId;
    private final String productName;
    private final int quantity;
    private final BigDecimal totalPrice;
    private final String status;
    private final LocalDateTime orderedAt;

    public Order(
            Long id,
            Long userId,
            Long productId,
            String productName,
            int quantity,
            BigDecimal totalPrice,
            String status,
            LocalDateTime orderedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderedAt = orderedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }
}
