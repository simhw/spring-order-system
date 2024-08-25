package com.example.shop.myproject.catalog.query.product;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryProduct {
    private List<ProductSummary> products = new ArrayList<>();
    private long totalCount;
    private int totalPages;

    public CategoryProduct() {
    }

    public CategoryProduct(List<ProductSummary> products, long totalCount, int totalPages) {
        this.products = products;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
    }
}
