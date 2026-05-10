package io.github.yonggyu.ecommerce.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private Long productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subtotal;
}
