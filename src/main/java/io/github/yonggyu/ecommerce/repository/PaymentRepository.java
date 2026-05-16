package io.github.yonggyu.ecommerce.repository;

import io.github.yonggyu.ecommerce.domain.Payment;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PaymentRepository {

    private final AtomicLong sequence = new AtomicLong(1);
    private final Map<Long, Payment> payments = new ConcurrentHashMap<>();

    public Long nextId() {
        return sequence.getAndIncrement();
    }

    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }
}
