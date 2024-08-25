package com.example.shop.myproject.catalog.query.product;

import com.example.shop.myproject.catalog.command.domain.product.Image;
import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductState;
import lombok.Data;

import java.util.List;

@Data
public class ProductSummary {
    private Long id;

    private String name;

    private int price;

    private String description;

    private Image image;

    private ProductState state;

    public ProductSummary() {
    }

    public ProductSummary(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.state = product.getState();
        setImage(product.getImages());
    }

    private void setImage(List<Image> images) {
        if (images.isEmpty()) {
            image = null;
            return;
        }
        image = images.get(0);
    }
}
