package io.github.yonggyu.ecommerce.service;

import io.github.yonggyu.ecommerce.domain.Product;
import io.github.yonggyu.ecommerce.dto.product.ProductResponse;
import io.github.yonggyu.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse getProductDetail(Long productId) {
        return toResponse(getProduct(productId));
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }

    public synchronized void decreaseStock(Long productId, int quantity) {
        Product product = getProduct(productId);
        product.decreaseStock(quantity);
        productRepository.save(product);
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}
