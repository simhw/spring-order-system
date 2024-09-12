package com.example.shop.myproject.order.commnad.domain;


import com.example.shop.myproject.catalog.command.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int price;

    private int count;

    public OrderLine(Product product, int price, int count) {
        this.product = product;
        this.price = price;
        this.count = count;
        this.price = getTotalPrice();
    }

    public int getTotalPrice() {
        return price * count;
    }
}
