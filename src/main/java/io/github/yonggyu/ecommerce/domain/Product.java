package io.github.yonggyu.ecommerce.domain;

import java.math.BigDecimal;

public class Product {

    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private int stock;

    public Product(Long id, String name, String description, BigDecimal price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("주문 수량은 1개 이상이어야 합니다.");
        }
        if (stock < quantity) {
            throw new IllegalArgumentException("상품 재고가 부족합니다.");
        }

        stock -= quantity;
    }
}
