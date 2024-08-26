package com.example.shop.myproject.catalog.query.product;


import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductState;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductData {
    private Long id;

    private String name;

    private String description;

    private int price;

    private ProductState state;

    private List<ImageData> images = new ArrayList<>();

    public ProductData(Product product) {
    }
}
