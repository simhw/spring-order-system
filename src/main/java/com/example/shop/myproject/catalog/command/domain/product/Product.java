package com.example.shop.myproject.catalog.command.domain.product;

import com.example.shop.myproject.common.domain.BaseEntity;
import com.example.shop.myproject.exception.NotEnoughStockException;
import com.example.shop.myproject.like.domain.Like;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
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

    @OneToMany(mappedBy = "product")
    private Set<ProductCategory> productCategories = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private List<Like> likes = new ArrayList<>();

    protected Product() {
    }

    public Product(Long id, String name) {
        this.id = id;
        setName(name);
    }

    public Product(Long id, String name, int price, int stock) {
        this.id = id;
        setName(name);
        this.price = price;
        this.stock = stock;
        this.state = ProductState.SALE;
    }

    public Product(String name, int price, int stock, String description, List<Image> images) {
        setName(name);
        setPrice(price);
        this.stock = stock;
        this.description = description;
        setImages(images);
        this.state = ProductState.SALE;
    }

    private void setName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new RuntimeException();
        }
        this.name = name;

    }

    public void setPrice(int price) {
        if (price >= 0) {
            throw new RuntimeException();
        }
        this.price = price;
    }

    private void setImages(List<Image> images) {
        this.images = images;
        for (Image image : images) {
            image.setProduct(this);
        }
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
            throw new NotEnoughStockException(String.valueOf(id));
        }
    }
}
