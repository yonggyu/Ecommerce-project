package io.github.yonggyu.ecommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductListResponse {

    private final List<ProductResponse> products;
}
