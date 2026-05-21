package io.github.yonggyu.ecommerce.repository;

import io.github.yonggyu.ecommerce.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
