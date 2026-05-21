package io.github.yonggyu.ecommerce.repository;

import io.github.yonggyu.ecommerce.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
