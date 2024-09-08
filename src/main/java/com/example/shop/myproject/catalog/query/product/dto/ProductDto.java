package com.example.shop.myproject.catalog.query.product.dto;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductState;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {
    private String name;
    private int price;
    private int stock;
    private String description;
    private ProductState state;
    private List<ImageDto> images = new ArrayList<>();
    private boolean liked;

    protected ProductDto() {
    }

    public ProductDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.description = product.getDescription();
        this.state = product.getState();
        this.images = product.getImages().stream()
                .map(ImageDto::new)
                .toList();
    }
}
