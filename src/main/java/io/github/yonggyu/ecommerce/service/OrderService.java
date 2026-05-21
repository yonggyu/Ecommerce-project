package io.github.yonggyu.ecommerce.service;

import io.github.yonggyu.ecommerce.domain.Order;
import io.github.yonggyu.ecommerce.domain.Product;
import io.github.yonggyu.ecommerce.dto.order.OrderCreateRequest;
import io.github.yonggyu.ecommerce.dto.order.OrderCreateResponse;
import io.github.yonggyu.ecommerce.dto.order.OrderResponse;
import io.github.yonggyu.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Transactional
    public OrderCreateResponse createOrder(OrderCreateRequest request) {
        Long userId = request != null && request.getUserId() != null ? request.getUserId() : 1L;
        Long productId = request != null && request.getProductId() != null ? request.getProductId() : 1L;
        int quantity = request != null && request.getQuantity() != null ? request.getQuantity() : 1;

        Product product = productService.getProduct(productId);
        productService.decreaseStock(productId, quantity);

        Order order = Order.builder()
                .userId(userId)
                .productId(productId)
                .productName(product.getName())
                .quantity(quantity)
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .status("CREATED")
                .orderedAt(LocalDateTime.now())
                .build();

        return toCreateResponse(orderRepository.save(order));
    }

    public OrderResponse getOrderDetail(Long orderId) {
        Order order = getOrder(orderId);

        return new OrderResponse(
                order.getId(),
                order.getId(),
                order.getProductName(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getOrderedAt()
        );
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
    }

    private OrderCreateResponse toCreateResponse(Order order) {
        return new OrderCreateResponse(
                order.getId(),
                order.getUserId(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getOrderedAt()
        );
    }
}
