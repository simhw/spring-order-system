package com.example.shop.myproject.catalog.command.domain.product;

import com.example.shop.myproject.catalog.command.domain.category.Category;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected ProductCategory() {
    }

    ProductCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }
}
