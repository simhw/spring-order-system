package com.example.shop.myproject.catalog.ui;

import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.catalog.query.category.CategoryData;
import com.example.shop.myproject.catalog.query.product.CategoryProduct;
import com.example.shop.myproject.catalog.query.product.ProductQueryService;
import com.example.shop.myproject.common.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductQueryService productQueryService;

    @GetMapping("/categories/{categoryId}")
    public String list(@PathVariable Long categoryId, Model model) {
        List<CategoryData> categories = productQueryService.getCategories();
        model.addAttribute("categories", categories);

        return "catalog/products";
    }
}
