package com.example.shop.myproject.catalog.query.product.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductQueryServiceV2Test {

    @Autowired
    private ProductQueryServiceV2 productQueryServiceV2;

    @Test
    void getProductInCategory() {
        Long categoryId = 111L;
        Pageable pageable = PageRequest.of(0, 10);
        productQueryServiceV2.getProductInCategory(categoryId, pageable);
    }

}