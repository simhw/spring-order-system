package com.example.shop.myproject.catalog.ui;

import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProductController {
    private final ProductRepository productRepository;
}
