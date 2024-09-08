package com.example.shop.myproject.catalog.query.product;

import com.example.shop.myproject.catalog.query.product.application.ProductQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductQueryServiceTest {

    @Autowired
    private ProductQueryService productQueryService;

    @Test
    void products() {
    }
}