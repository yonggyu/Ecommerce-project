package io.github.yonggyu.ecommerce.service;

import io.github.yonggyu.ecommerce.dto.product.ProductResponse;
import io.github.yonggyu.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(new ProductRepository());
    }

    @Test
    void getProducts_returnsProductList() {
        List<ProductResponse> products = productService.getProducts();

        assertThat(products).hasSize(3);
        assertThat(products.get(0).getId()).isEqualTo(1L);
        assertThat(products.get(0).getName()).isEqualTo("MacBook Air M4");
    }

    @Test
    void getProductDetail_returnsProductDetail() {
        ProductResponse product = productService.getProductDetail(1L);

        assertThat(product.getName()).isEqualTo("MacBook Air M4");
        assertThat(product.getPrice()).isEqualByComparingTo(new BigDecimal("1590000"));
        assertThat(product.getStock()).isEqualTo(10);
    }

    @Test
    void getProductDetail_throwsWhenProductDoesNotExist() {
        assertThatThrownBy(() -> productService.getProductDetail(999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상품을 찾을 수 없습니다.");
    }

    @Test
    void decreaseStock_reducesProductStock() {
        productService.decreaseStock(1L, 3);

        ProductResponse product = productService.getProductDetail(1L);
        assertThat(product.getStock()).isEqualTo(7);
    }

    @Test
    void decreaseStock_throwsWhenStockIsNotEnough() {
        assertThatThrownBy(() -> productService.decreaseStock(1L, 11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상품 재고가 부족합니다.");

        assertThat(productService.getProductDetail(1L).getStock()).isEqualTo(10);
    }
}
