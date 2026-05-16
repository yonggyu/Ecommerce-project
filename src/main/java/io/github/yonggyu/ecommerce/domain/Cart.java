package io.github.yonggyu.ecommerce.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Cart {

    private final Long id;
    private final Long userId;
    private final Map<Long, CartItem> items = new LinkedHashMap<>();

    public Cart(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public void addItem(Product product, int quantity) {
        validateQuantity(quantity);
        CartItem item = items.get(product.getId());

        if (item == null) {
            items.put(product.getId(), new CartItem(product.getId(), product.getName(), product.getPrice(), quantity));
            return;
        }

        item.changeQuantity(item.getQuantity() + quantity);
    }

    public void updateItemQuantity(Long productId, int quantity) {
        validateQuantity(quantity);
        CartItem item = findItem(productId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 상품을 찾을 수 없습니다."));
        item.changeQuantity(quantity);
    }

    public void deleteItem(Long productId) {
        if (items.remove(productId) == null) {
            throw new IllegalArgumentException("장바구니 상품을 찾을 수 없습니다.");
        }
    }

    private Optional<CartItem> findItem(Long productId) {
        return Optional.ofNullable(items.get(productId));
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("상품 수량은 1개 이상이어야 합니다.");
        }
    }
}
