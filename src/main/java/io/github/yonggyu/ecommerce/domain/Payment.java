package io.github.yonggyu.ecommerce.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private final Long id;
    private final Long orderId;
    private final BigDecimal amount;
    private final String paymentMethod;
    private final String status;
    private final LocalDateTime paidAt;

    public Payment(Long id, Long orderId, BigDecimal amount, String paymentMethod, String status, LocalDateTime paidAt) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paidAt = paidAt;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }
}
