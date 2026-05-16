package io.github.yonggyu.ecommerce.repository;

import io.github.yonggyu.ecommerce.domain.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    public ProductRepository() {
        save(new Product(1L, "MacBook Air M4", "Apple Silicon M4 탑재", new BigDecimal("1590000"), 10));
        save(new Product(2L, "iPhone 16 Pro", "A18 Pro 칩과 프로 카메라 시스템", new BigDecimal("1550000"), 15));
        save(new Product(3L, "AirPods Pro 2", "액티브 노이즈 캔슬링 무선 이어폰", new BigDecimal("349000"), 30));
    }

    public List<Product> findAll() {
        return products.values()
                .stream()
                .sorted(Comparator.comparing(Product::getId))
                .toList();
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }
}
