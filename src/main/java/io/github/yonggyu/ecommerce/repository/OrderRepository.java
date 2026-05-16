package io.github.yonggyu.ecommerce.repository;

import io.github.yonggyu.ecommerce.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {

    private final AtomicLong sequence = new AtomicLong(1);
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();

    public Long nextId() {
        return sequence.getAndIncrement();
    }

    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }
}
