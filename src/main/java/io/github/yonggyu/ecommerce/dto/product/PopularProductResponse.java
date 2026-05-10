package io.github.yonggyu.ecommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularProductResponse {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Integer rank;
    private Long salesCount;
}
