package io.github.yonggyu.ecommerce.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Long orderNumber;
    private String productName;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime orderedAt;
}
