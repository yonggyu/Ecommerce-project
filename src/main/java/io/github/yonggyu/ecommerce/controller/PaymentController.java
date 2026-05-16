package io.github.yonggyu.ecommerce.controller;

import io.github.yonggyu.ecommerce.common.response.BaseResponse;
import io.github.yonggyu.ecommerce.dto.payment.PaymentRequest;
import io.github.yonggyu.ecommerce.dto.payment.PaymentResponse;
import io.github.yonggyu.ecommerce.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment API", description = "결제 API")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @Operation(summary = "결제 요청")
    @ApiResponse(responseCode = "200", description = "결제 성공")
    public ResponseEntity<BaseResponse<PaymentResponse>> requestPayment(
            @RequestBody(required = false) PaymentRequest request
    ) {
        PaymentResponse response = paymentService.requestPayment(request);

        return ResponseEntity.ok(
                BaseResponse.success("결제 성공", response)
        );
    }
}
