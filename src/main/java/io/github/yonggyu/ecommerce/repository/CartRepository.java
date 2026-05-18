package io.github.yonggyu.ecommerce.repository;

import io.github.yonggyu.ecommerce.domain.Cart;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CartRepository {

    private final Map<Long, Cart> carts = new ConcurrentHashMap<>();

    public CartRepository() {
        save(new Cart(1L, 1L));
    }

    public Optional<Cart> findById(Long id) {
        return Optional.ofNullable(carts.get(id));
    }

    public Cart save(Cart cart) {
        carts.put(cart.getId(), cart);
        return cart;
    }
}
