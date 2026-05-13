package io.github.yonggyu.ecommerce.controller;

import io.github.yonggyu.ecommerce.common.response.BaseResponse;
import io.github.yonggyu.ecommerce.dto.cart.CartItemResponse;
import io.github.yonggyu.ecommerce.dto.cart.CartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Cart API", description = "장바구니 API")
public class CartController {

    @GetMapping("/{cartId}")
    @Operation(summary = "장바구니 조회", description = "장바구니 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "장바구니 조회 성공")
    public ResponseEntity<BaseResponse<CartResponse>> getCart(
            @PathVariable Long cartId
    ) {

        CartResponse response = new CartResponse(
                cartId,
                1L,
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
                new BigDecimal("1590000")
        );

        return ResponseEntity.ok(
                BaseResponse.success("장바구니 조회 성공", response)
        );
    }

    @PostMapping("/{cartId}/items")
    @Operation(summary = "상품 추가", description = "장바구니에 상품을 추가합니다.")
    @ApiResponse(responseCode = "200", description = "상품 추가 성공")
    public ResponseEntity<BaseResponse<CartResponse>> addCartItem(
            @PathVariable Long cartId
    ) {

        CartResponse response = new CartResponse(
                cartId,
                1L,
                List.of(
                        new CartItemResponse(
                                1L,
                                "MacBook Air M4",
                                new BigDecimal("1590000"),
                                2,
                                new BigDecimal("3180000")
                        )
                ),
                2,
                new BigDecimal("3180000")
        );

        return ResponseEntity.ok(
                BaseResponse.success("상품 추가 성공", response)
        );
    }

    @PutMapping("/{cartId}/items/{productId}")
    @Operation(summary = "상품 수량 변경", description = "장바구니 상품 수량을 변경합니다.")
    @ApiResponse(responseCode = "200", description = "상품 수량 변경 성공")
    public ResponseEntity<BaseResponse<CartResponse>> updateCartItemQuantity(
            @PathVariable Long cartId,
            @PathVariable Long productId
    ) {

        CartResponse response = new CartResponse(
                cartId,
                1L,
                List.of(
                        new CartItemResponse(
                                productId,
                                "MacBook Air M4",
                                new BigDecimal("1590000"),
                                3,
                                new BigDecimal("4770000")
                        )
                ),
                3,
                new BigDecimal("4770000")
        );

        return ResponseEntity.ok(
                BaseResponse.success("상품 수량 변경 성공", response)
        );
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    @Operation(summary = "상품 삭제", description = "장바구니에서 상품을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "상품 삭제 성공")
    public ResponseEntity<BaseResponse<Void>> deleteCartItem(
            @PathVariable Long cartId,
            @PathVariable Long productId
    ) {

        return ResponseEntity.ok(
                BaseResponse.success("상품 삭제 성공", null)
        );
    }
}
