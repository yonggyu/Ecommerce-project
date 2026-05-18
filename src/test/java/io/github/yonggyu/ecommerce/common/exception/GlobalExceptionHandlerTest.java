package io.github.yonggyu.ecommerce.common.exception;

import io.github.yonggyu.ecommerce.common.response.BaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    @Test
    void handleIllegalArgumentException_returnsFailResponse() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        ResponseEntity<BaseResponse<Void>> response = handler.handleIllegalArgumentException(
                new IllegalArgumentException("상품을 찾을 수 없습니다.")
        );

        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getMessage()).isEqualTo("상품을 찾을 수 없습니다.");
        assertThat(response.getBody().getData()).isNull();
    }
}
