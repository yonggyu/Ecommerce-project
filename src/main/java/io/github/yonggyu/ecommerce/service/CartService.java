package io.github.yonggyu.ecommerce.service;

import io.github.yonggyu.ecommerce.domain.Cart;
import io.github.yonggyu.ecommerce.domain.CartItem;
import io.github.yonggyu.ecommerce.domain.Product;
import io.github.yonggyu.ecommerce.dto.cart.CartItemRequest;
import io.github.yonggyu.ecommerce.dto.cart.CartItemResponse;
import io.github.yonggyu.ecommerce.dto.cart.CartResponse;
import io.github.yonggyu.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public CartResponse getCart(Long cartId) {
        return toResponse(getCartEntity(cartId));
    }

    @Transactional
    public CartResponse addCartItem(Long cartId, CartItemRequest request) {
        Cart cart = getCartEntity(cartId);
        Long productId = request != null && request.getProductId() != null ? request.getProductId() : 1L;
        int quantity = request != null && request.getQuantity() != null ? request.getQuantity() : 1;
        Product product = productService.getProduct(productId);

        cart.addItem(product, quantity);
        return toResponse(cartRepository.save(cart));
    }

    @Transactional
    public CartResponse updateCartItemQuantity(Long cartId, Long productId, CartItemRequest request) {
        int quantity = request != null && request.getQuantity() != null ? request.getQuantity() : 1;
        Cart cart = getCartEntity(cartId);

        cart.updateItemQuantity(productId, quantity);
        return toResponse(cartRepository.save(cart));
    }

    @Transactional
    public void deleteCartItem(Long cartId, Long productId) {
        Cart cart = getCartEntity(cartId);
        cart.deleteItem(productId);
        cartRepository.save(cart);
    }

    private Cart getCartEntity(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니를 찾을 수 없습니다."));
    }

    private CartResponse toResponse(Cart cart) {
        List<CartItemResponse> itemResponses = cart.getItems()
                .stream()
                .map(this::toItemResponse)
                .toList();

        int totalQuantity = itemResponses.stream()
                .mapToInt(CartItemResponse::getQuantity)
                .sum();

        BigDecimal totalPrice = itemResponses.stream()
                .map(CartItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(cart.getId(), cart.getUserId(), itemResponses, totalQuantity, totalPrice);
    }

    private CartItemResponse toItemResponse(CartItem item) {
        return new CartItemResponse(
                item.getProductId(),
                item.getProductName(),
                item.getPrice(),
                item.getQuantity(),
                item.getSubtotal()
        );
    }
}
