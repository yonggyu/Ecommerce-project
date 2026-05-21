package io.github.yonggyu.ecommerce.repository;

import io.github.yonggyu.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
