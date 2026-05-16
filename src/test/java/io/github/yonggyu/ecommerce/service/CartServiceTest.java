package io.github.yonggyu.ecommerce.service;

import io.github.yonggyu.ecommerce.dto.cart.CartItemRequest;
import io.github.yonggyu.ecommerce.dto.cart.CartResponse;
import io.github.yonggyu.ecommerce.repository.CartRepository;
import io.github.yonggyu.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    void setUp() {
        ProductService productService = new ProductService(new ProductRepository());
        cartService = new CartService(new CartRepository(), productService);
    }

    @Test
    void getCart_returnsEmptyCart() {
        CartResponse cart = cartService.getCart(1L);

        assertThat(cart.getId()).isEqualTo(1L);
        assertThat(cart.getUserId()).isEqualTo(1L);
        assertThat(cart.getItems()).isEmpty();
        assertThat(cart.getTotalQuantity()).isZero();
        assertThat(cart.getTotalPrice()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void addCartItem_addsProductToCart() {
        CartResponse cart = cartService.addCartItem(1L, new CartItemRequest(1L, 2));

        assertThat(cart.getItems()).hasSize(1);
        assertThat(cart.getItems().get(0).getName()).isEqualTo("MacBook Air M4");
        assertThat(cart.getItems().get(0).getQuantity()).isEqualTo(2);
        assertThat(cart.getTotalQuantity()).isEqualTo(2);
        assertThat(cart.getTotalPrice()).isEqualByComparingTo(new BigDecimal("3180000"));
    }

    @Test
    void addCartItem_accumulatesQuantityWhenSameProductExists() {
        cartService.addCartItem(1L, new CartItemRequest(1L, 2));

        CartResponse cart = cartService.addCartItem(1L, new CartItemRequest(1L, 3));

        assertThat(cart.getItems()).hasSize(1);
        assertThat(cart.getItems().get(0).getQuantity()).isEqualTo(5);
        assertThat(cart.getTotalPrice()).isEqualByComparingTo(new BigDecimal("7950000"));
    }

    @Test
    void updateCartItemQuantity_changesQuantity() {
        cartService.addCartItem(1L, new CartItemRequest(1L, 2));

        CartResponse cart = cartService.updateCartItemQuantity(1L, 1L, new CartItemRequest(1L, 4));

        assertThat(cart.getItems().get(0).getQuantity()).isEqualTo(4);
        assertThat(cart.getTotalPrice()).isEqualByComparingTo(new BigDecimal("6360000"));
    }

    @Test
    void deleteCartItem_removesProductFromCart() {
        cartService.addCartItem(1L, new CartItemRequest(1L, 2));

        cartService.deleteCartItem(1L, 1L);

        CartResponse cart = cartService.getCart(1L);
        assertThat(cart.getItems()).isEmpty();
        assertThat(cart.getTotalQuantity()).isZero();
    }

    @Test
    void addCartItem_throwsWhenQuantityIsInvalid() {
        assertThatThrownBy(() -> cartService.addCartItem(1L, new CartItemRequest(1L, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상품 수량은 1개 이상이어야 합니다.");
    }

    @Test
    void deleteCartItem_throwsWhenCartItemDoesNotExist() {
        assertThatThrownBy(() -> cartService.deleteCartItem(1L, 999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("장바구니 상품을 찾을 수 없습니다.");
    }
}
