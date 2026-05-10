package io.github.yonggyu.ecommerce.controller;

import io.github.yonggyu.ecommerce.dto.cart.CartItemResponse;
import io.github.yonggyu.ecommerce.dto.cart.CartResponse;
import io.github.yonggyu.ecommerce.dto.order.OrderResponse;
import io.github.yonggyu.ecommerce.dto.payment.PaymentResponse;
import io.github.yonggyu.ecommerce.dto.product.PopularProductResponse;
import io.github.yonggyu.ecommerce.dto.product.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "E-Commerce API", description = "쇼핑몰 API")
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "404", description = "데이터를 찾을 수 없음"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
})
public class EcommerceController {

    @GetMapping("/products")
    @Operation(summary = "상품 목록 조회", description = "판매 중인 상품 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상품 목록 조회 성공"),
    })
    public List<ProductResponse> getProducts() {
        return List.of(
                new ProductResponse(
                        1L,
                        "MacBook Air M4",
                        "Apple Silicon M4 탑재",
                        new BigDecimal("1590000"),
                        10
                ),
                new ProductResponse(
                        2L,
                        "iPhone 17",
                        "Dynamic Island 지원",
                        new BigDecimal("1350000"),
                        25
                )
        );
    }

    @GetMapping("/products/{productId}")
    @Operation(summary = "상품 상세 조회", description = "특정 상품의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "상품 상세 정보 조회 성공")
    public ProductResponse getProductDetail(@PathVariable Long productId) {
        return new ProductResponse(
                productId,
                "MacBook Air M4",
                "Apple Silicon M4 탑재",
                new BigDecimal("1590000"),
                10
        );
    }

    @GetMapping("/products/popular")
    @Operation(summary = "인기 상품 조회", description = "인기 상품 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "인기 상품 목록 조회 성공")
    public PopularProductResponse getPopularProducts() {
        return new PopularProductResponse(
                1L,
                "MacBook Air M4",
                "Apple Silicon M4 탑재",
                new BigDecimal("1590000"),
                10,
                1,
                152L
        );
    }

    @PostMapping("/orders")
    @Operation(summary = "주문 생성", description = "주문을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "주문 생성 성공")
    public CartResponse createOrder() {
        return new CartResponse(
                1L,
                2L,
                List.of(
                        new CartItemResponse(
                                1L,
                                "MacBook Air M4",
                                new BigDecimal("1590000"),
                                1,
                                new BigDecimal("1590000")
                        )
                ),
                1,
                new BigDecimal(1 * 1590000)
        );
    }

    @GetMapping("/orders/{orderId}")
    @Operation(summary = "주문 상세 조회", description = "주문 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "주문 상세 정보 조회 성공")
    public OrderResponse getOrderDetail(@PathVariable Long orderId) {
        return new OrderResponse(
                1L,
                11L,
                "MacBook Air M4",
                1,
                new BigDecimal(1 * 1590000),
                LocalDate.now().atStartOfDay()
        );
    }

    @PostMapping("/payments")
    @Operation(summary = "결제 요청", description = "주문 결제를 요청합니다.")
    @ApiResponse(responseCode = "200", description = "주문 결제 성공")
    public PaymentResponse requestPayment() {
        return new PaymentResponse(
                1L,
                1L,
                new BigDecimal(1590000),
                "CREDIT_CARD",
                "SUCCESS",
                LocalDate.now().atStartOfDay()
        );
    }
}