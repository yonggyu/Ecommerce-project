package io.github.yonggyu.ecommerce.controller;

import io.github.yonggyu.ecommerce.common.response.BaseResponse;
import io.github.yonggyu.ecommerce.dto.cart.CartItemRequest;
import io.github.yonggyu.ecommerce.dto.cart.CartResponse;
import io.github.yonggyu.ecommerce.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Cart API", description = "장바구니 API")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}")
    @Operation(summary = "장바구니 조회", description = "장바구니 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "장바구니 조회 성공")
    public ResponseEntity<BaseResponse<CartResponse>> getCart(
            @PathVariable Long cartId
    ) {
        CartResponse response = cartService.getCart(cartId);

        return ResponseEntity.ok(
                BaseResponse.success("장바구니 조회 성공", response)
        );
    }

    @PostMapping("/{cartId}/items")
    @Operation(summary = "상품 추가", description = "장바구니에 상품을 추가합니다.")
    @ApiResponse(responseCode = "200", description = "상품 추가 성공")
    public ResponseEntity<BaseResponse<CartResponse>> addCartItem(
            @PathVariable Long cartId,
            @RequestBody(required = false) CartItemRequest request
    ) {
        CartResponse response = cartService.addCartItem(cartId, request);

        return ResponseEntity.ok(
                BaseResponse.success("상품 추가 성공", response)
        );
    }

    @PutMapping("/{cartId}/items/{productId}")
    @Operation(summary = "상품 수량 변경", description = "장바구니 상품 수량을 변경합니다.")
    @ApiResponse(responseCode = "200", description = "상품 수량 변경 성공")
    public ResponseEntity<BaseResponse<CartResponse>> updateCartItemQuantity(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestBody(required = false) CartItemRequest request
    ) {
        CartResponse response = cartService.updateCartItemQuantity(cartId, productId, request);

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
        cartService.deleteCartItem(cartId, productId);

        return ResponseEntity.ok(
                BaseResponse.success("상품 삭제 성공", null)
        );
    }
}
