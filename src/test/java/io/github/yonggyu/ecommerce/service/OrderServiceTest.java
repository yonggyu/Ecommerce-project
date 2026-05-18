package io.github.yonggyu.ecommerce.service;

import io.github.yonggyu.ecommerce.dto.order.OrderCreateRequest;
import io.github.yonggyu.ecommerce.dto.order.OrderCreateResponse;
import io.github.yonggyu.ecommerce.dto.order.OrderResponse;
import io.github.yonggyu.ecommerce.repository.OrderRepository;
import io.github.yonggyu.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderServiceTest {

    private ProductService productService;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(new ProductRepository());
        orderService = new OrderService(new OrderRepository(), productService);
    }

    @Test
    void createOrder_createsOrderAndDecreasesStockAtOrderCreation() {
        OrderCreateResponse response = orderService.createOrder(new OrderCreateRequest(7L, 1L, 2));

        assertThat(response.getOrderId()).isEqualTo(1L);
        assertThat(response.getUserId()).isEqualTo(7L);
        assertThat(response.getTotalQuantity()).isEqualTo(2);
        assertThat(response.getTotalPrice()).isEqualByComparingTo(new BigDecimal("3180000"));
        assertThat(response.getOrderStatus()).isEqualTo("CREATED");
        assertThat(productService.getProductDetail(1L).getStock()).isEqualTo(8);
    }

    @Test
    void createOrder_throwsWhenStockIsNotEnough() {
        assertThatThrownBy(() -> orderService.createOrder(new OrderCreateRequest(1L, 1L, 11)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상품 재고가 부족합니다.");

        assertThat(productService.getProductDetail(1L).getStock()).isEqualTo(10);
    }

    @Test
    void getOrderDetail_returnsCreatedOrder() {
        OrderCreateResponse created = orderService.createOrder(new OrderCreateRequest(1L, 2L, 1));

        OrderResponse response = orderService.getOrderDetail(created.getOrderId());

        assertThat(response.getOrderId()).isEqualTo(created.getOrderId());
        assertThat(response.getProductName()).isEqualTo("iPhone 16 Pro");
        assertThat(response.getQuantity()).isEqualTo(1);
        assertThat(response.getTotalPrice()).isEqualByComparingTo(new BigDecimal("1550000"));
    }

    @Test
    void getOrderDetail_throwsWhenOrderDoesNotExist() {
        assertThatThrownBy(() -> orderService.getOrderDetail(999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문을 찾을 수 없습니다.");
    }
}
