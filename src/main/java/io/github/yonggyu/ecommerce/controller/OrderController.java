package io.github.yonggyu.ecommerce.controller;

import io.github.yonggyu.ecommerce.common.response.BaseResponse;
import io.github.yonggyu.ecommerce.dto.order.OrderCreateRequest;
import io.github.yonggyu.ecommerce.dto.order.OrderCreateResponse;
import io.github.yonggyu.ecommerce.dto.order.OrderResponse;
import io.github.yonggyu.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order API", description = "주문 API")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "주문 생성")
    @ApiResponse(responseCode = "200", description = "주문 생성 성공")
    public ResponseEntity<BaseResponse<OrderCreateResponse>> createOrder(
            @RequestBody(required = false) OrderCreateRequest request
    ) {
        OrderCreateResponse response = orderService.createOrder(request);

        return ResponseEntity.ok(
                BaseResponse.success("주문 생성 성공", response)
        );
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "주문 상세 조회")
    @ApiResponse(responseCode = "200", description = "주문 상세 조회 성공")
    public ResponseEntity<BaseResponse<OrderResponse>> getOrderDetail(
            @PathVariable Long orderId
    ) {
        OrderResponse response = orderService.getOrderDetail(orderId);

        return ResponseEntity.ok(
                BaseResponse.success("주문 상세 조회 성공", response)
        );
    }
}
