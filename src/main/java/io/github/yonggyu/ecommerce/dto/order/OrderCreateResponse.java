package io.github.yonggyu.ecommerce.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateResponse {

    private Long orderId;
    private Long userId;
    private Integer totalQuantity;
    private BigDecimal totalPrice;
    private String orderStatus;
    private LocalDateTime orderedAt;
}