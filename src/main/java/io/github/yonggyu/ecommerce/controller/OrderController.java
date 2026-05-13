package io.github.yonggyu.ecommerce.controller;

import io.github.yonggyu.ecommerce.common.response.BaseResponse;
import io.github.yonggyu.ecommerce.dto.order.OrderCreateResponse;
import io.github.yonggyu.ecommerce.dto.order.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order API", description = "주문 API")
public class OrderController {

    @PostMapping
    @Operation(summary = "주문 생성")
    @ApiResponse(responseCode = "200", description = "주문 생성 성공")
    public ResponseEntity<BaseResponse<OrderCreateResponse>> createOrder() {

        OrderCreateResponse response = new OrderCreateResponse(
                1L,
                1L,
                2,
                new BigDecimal("3180000"),
                "CREATED",
                LocalDateTime.now()
        );

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

        OrderResponse response = new OrderResponse(
                orderId,
                11L,
                "MacBook Air M4",
                1,
                new BigDecimal("1590000"),
                LocalDate.now().atStartOfDay()
        );

        return ResponseEntity.ok(
                BaseResponse.success("주문 상세 조회 성공", response)
        );
    }
}