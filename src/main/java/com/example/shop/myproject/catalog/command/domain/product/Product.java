package com.example.shop.myproject.catalog.command.domain.product;

import com.example.shop.myproject.catalog.command.domain.category.Category;
import com.example.shop.myproject.common.domain.BaseEntity;
import com.example.shop.myproject.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int price;

    private int stock;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductState state;

    @OneToMany(mappedBy = "product")
    @OrderColumn(name = "no")
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected Product() {
    }

    public Product(Long id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.state = ProductState.SELLING;
    }

    public Product(String name, int price, int stock, String description, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.state = ProductState.SELLING;
    }

    public void increaseStock(int quantity) {
        stock += quantity;
    }

    public void decreaseStock(int quantity) {
        validateStock(quantity);
        stock -= quantity;
    }

    public void validateStock(int quantity) {
        int restStock = stock - quantity;

        if (restStock < 0) {
            throw new NotEnoughStockException("Stock: " + stock);
        }
    }
}
