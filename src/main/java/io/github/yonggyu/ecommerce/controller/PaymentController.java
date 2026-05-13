package io.github.yonggyu.ecommerce.controller;

import io.github.yonggyu.ecommerce.common.response.BaseResponse;
import io.github.yonggyu.ecommerce.dto.payment.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment API", description = "결제 API")
public class PaymentController {

    @PostMapping
    @Operation(summary = "결제 요청")
    @ApiResponse(responseCode = "200", description = "결제 성공")
    public ResponseEntity<BaseResponse<PaymentResponse>> requestPayment() {

        PaymentResponse response = new PaymentResponse(
                1L,
                1L,
                new BigDecimal("1590000"),
                "CREDIT_CARD",
                "SUCCESS",
                LocalDate.now().atStartOfDay()
        );

        return ResponseEntity.ok(
                BaseResponse.success("결제 성공", response)
        );
    }
}
