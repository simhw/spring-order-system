package com.example.shop.myproject.catalog.ui;


import com.example.shop.myproject.catalog.query.product.CategoryProduct;
import com.example.shop.myproject.catalog.query.product.ProductQueryService;
import com.example.shop.myproject.catalog.query.category.CategorySummary;
import com.example.shop.myproject.common.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class ProductRestController {

    private final ProductQueryService productQueryService;

    @GetMapping("/api/categories")
    public List<CategorySummary> getAll() {
        return productQueryService.getAllCategory();
    }

    @GetMapping("/api/categories/{categoryId}")
    public ApiResponse<CategoryProduct> list(@PathVariable Long categoryId, Pageable pageable) {
        try {
            CategoryProduct data = productQueryService.getProductInCategory(categoryId, pageable);
            return new ApiResponse<>(HttpStatus.OK, data);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, null);

        }
    }
}
