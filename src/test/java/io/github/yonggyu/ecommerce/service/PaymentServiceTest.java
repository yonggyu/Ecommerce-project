package io.github.yonggyu.ecommerce.service;

import io.github.yonggyu.ecommerce.dto.order.OrderCreateRequest;
import io.github.yonggyu.ecommerce.dto.order.OrderCreateResponse;
import io.github.yonggyu.ecommerce.dto.payment.PaymentRequest;
import io.github.yonggyu.ecommerce.dto.payment.PaymentResponse;
import io.github.yonggyu.ecommerce.repository.OrderRepository;
import io.github.yonggyu.ecommerce.repository.PaymentRepository;
import io.github.yonggyu.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentServiceTest {

    private OrderService orderService;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        ProductService productService = new ProductService(new ProductRepository());
        orderService = new OrderService(new OrderRepository(), productService);
        paymentService = new PaymentService(new PaymentRepository(), orderService);
    }

    @Test
    void requestPayment_paysCreatedOrder() {
        OrderCreateResponse order = orderService.createOrder(new OrderCreateRequest(1L, 1L, 1));

        PaymentResponse payment = paymentService.requestPayment(new PaymentRequest(order.getOrderId(), "KAKAO_PAY"));

        assertThat(payment.getPaymentId()).isEqualTo(1L);
        assertThat(payment.getOrderId()).isEqualTo(order.getOrderId());
        assertThat(payment.getAmount()).isEqualByComparingTo(new BigDecimal("1590000"));
        assertThat(payment.getPaymentMethod()).isEqualTo("KAKAO_PAY");
        assertThat(payment.getPaymentStatus()).isEqualTo("SUCCESS");
    }

    @Test
    void requestPayment_throwsWhenOrderDoesNotExist() {
        assertThatThrownBy(() -> paymentService.requestPayment(new PaymentRequest(999L, "CREDIT_CARD")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문을 찾을 수 없습니다.");
    }
}
