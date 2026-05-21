package io.github.yonggyu.ecommerce.controller;

import io.github.yonggyu.ecommerce.common.response.BaseResponse;
import io.github.yonggyu.ecommerce.dto.product.ProductListResponse;
import io.github.yonggyu.ecommerce.dto.product.ProductResponse;
import io.github.yonggyu.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "상품 목록 조회")
    @ApiResponse(responseCode = "200", description = "상품 목록 조회 성공")
    public ResponseEntity<BaseResponse<ProductListResponse>> getProducts() {
        List<ProductResponse> products = productService.getProducts();

        return ResponseEntity.ok(
                BaseResponse.success("상품 목록 조회 성공", new ProductListResponse(products))
        );
    }

    @GetMapping("/{productId}")
    @Operation(summary = "상품 상세 조회")
    @ApiResponse(responseCode = "200", description = "상품 상세 조회 성공")
    public ResponseEntity<BaseResponse<ProductResponse>> getProductDetail(
            @PathVariable Long productId
    ) {
        ProductResponse product = productService.getProductDetail(productId);

        return ResponseEntity.ok(
                BaseResponse.success("상품 상세 조회 성공", product)
        );
    }
}