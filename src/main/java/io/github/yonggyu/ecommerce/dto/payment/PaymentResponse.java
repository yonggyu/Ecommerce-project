package io.github.yonggyu.ecommerce.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private Long paymentId; //결제 ID
    private Long orderId;
    private BigDecimal amount;
    private String paymentMethod; //결제 수단
    private String paymentStatus; //결제 상태 (SUCCESS, FAILED, CANCELED)
    private LocalDateTime paidAt; //결제 시각
}
