package com.example.shop.myproject.catalog.query.product.application;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductListView {
    private List<ProductSummary> products = new ArrayList<>();
    private long totalCount;
    private int totalPages;

    public ProductListView() {
    }

    public ProductListView(List<ProductSummary> products, long totalCount, int totalPages) {
        this.products = products;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
    }
}
