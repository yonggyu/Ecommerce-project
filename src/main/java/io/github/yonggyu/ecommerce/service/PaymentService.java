package io.github.yonggyu.ecommerce.service;

import io.github.yonggyu.ecommerce.domain.Order;
import io.github.yonggyu.ecommerce.domain.Payment;
import io.github.yonggyu.ecommerce.dto.payment.PaymentRequest;
import io.github.yonggyu.ecommerce.dto.payment.PaymentResponse;
import io.github.yonggyu.ecommerce.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public PaymentService(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    @Transactional
    public PaymentResponse requestPayment(PaymentRequest request) {
        Long orderId = request != null && request.getOrderId() != null ? request.getOrderId() : 1L;
        String paymentMethod = request != null && request.getPaymentMethod() != null
                ? request.getPaymentMethod()
                : "CREDIT_CARD";

        Order order = orderService.getOrder(orderId);
        Payment payment = Payment.builder()
                .orderId(order.getId())
                .amount(order.getTotalPrice())
                .paymentMethod(paymentMethod)
                .status("SUCCESS")
                .paidAt(LocalDateTime.now())
                .build();

        return toResponse(paymentRepository.save(payment));
    }

    private PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getPaidAt()
        );
    }
}
