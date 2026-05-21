package io.github.yonggyu.ecommerce.config;

import io.github.yonggyu.ecommerce.domain.Cart;
import io.github.yonggyu.ecommerce.domain.Product;
import io.github.yonggyu.ecommerce.repository.CartRepository;
import io.github.yonggyu.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeData(ProductRepository productRepository, CartRepository cartRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.saveAll(List.of(
                        Product.builder()
                                .name("MacBook Air M4")
                                .description("Apple Silicon M4 탑재")
                                .price(new BigDecimal("1590000"))
                                .stock(10)
                                .build(),
                        Product.builder()
                                .name("iPhone 16 Pro")
                                .description("A18 Pro 칩과 프로 카메라 시스템")
                                .price(new BigDecimal("1550000"))
                                .stock(15)
                                .build(),
                        Product.builder()
                                .name("AirPods Pro 2")
                                .description("액티브 노이즈 캔슬링 무선 이어폰")
                                .price(new BigDecimal("349000"))
                                .stock(30)
                                .build()
                ));
            }

            if (cartRepository.count() == 0) {
                cartRepository.save(Cart.builder()
                        .userId(1L)
                        .build());
            }
        };
    }
}
