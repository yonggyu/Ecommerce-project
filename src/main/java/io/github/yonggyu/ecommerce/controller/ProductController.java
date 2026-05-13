package io.github.yonggyu.ecommerce.controller;

import io.github.yonggyu.ecommerce.common.response.BaseResponse;
import io.github.yonggyu.ecommerce.dto.product.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    @Operation(summary = "상품 목록 조회")
    @ApiResponse(responseCode = "200", description = "상품 목록 조회 성공")
    public ResponseEntity<BaseResponse<List<ProductResponse>>> getProducts() {

        List<ProductResponse> products = List.of(
                new ProductResponse(
                        1L,
                        "MacBook Air M4",
                        "Apple Silicon M4 탑재",
                        new BigDecimal("1590000"),
                        10
                )
        );

        return ResponseEntity.ok(
                BaseResponse.success("상품 목록 조회 성공", products)
        );
    }

    @GetMapping("/{productId}")
    @Operation(summary = "상품 상세 조회")
    @ApiResponse(responseCode = "200", description = "상품 상세 조회 성공")
    public ResponseEntity<BaseResponse<ProductResponse>> getProductDetail(
            @PathVariable Long productId
    ) {

        ProductResponse product = new ProductResponse(
                productId,
                "MacBook Air M4",
                "Apple Silicon M4 탑재",
                new BigDecimal("1590000"),
                10
        );

        return ResponseEntity.ok(
                BaseResponse.success("상품 상세 조회 성공", product)
        );
    }
}