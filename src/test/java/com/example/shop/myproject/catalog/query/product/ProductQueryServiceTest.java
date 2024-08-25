package com.example.shop.myproject.catalog.query.product;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProductQueryServiceTest {

    @Autowired
    private ProductQueryService productQueryService;

    @Test
    void products() {
    }
}