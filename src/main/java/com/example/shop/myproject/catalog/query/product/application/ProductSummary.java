package com.example.shop.myproject.catalog.query.product.application;

import com.example.shop.myproject.catalog.command.domain.product.Image;
import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductState;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductSummary {
    private Long id;
    private String name;
    private int price;
    private String thumbnail;
    private ProductState state;

    public ProductSummary() {
    }

    public ProductSummary(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.state = product.getState();
        setThumbnail(product.getImages());
    }

    private void setThumbnail(List<Image> images) {
        if (!images.isEmpty()) {
            thumbnail = images.get(0).getUrl();
            return;
        }
        thumbnail = null;
    }
}
