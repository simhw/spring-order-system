package com.example.shop.myproject.catalog.ui;


import com.example.shop.myproject.auth.UserDetailsImpl;
import com.example.shop.myproject.catalog.query.category.CategoryDto;
import com.example.shop.myproject.catalog.query.category.CategoryQueryService;
import com.example.shop.myproject.catalog.query.product.application.ProductQueryServiceV2;
import com.example.shop.myproject.catalog.query.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductQueryServiceV2 productQueryService;
    private final CategoryQueryService categoryQueryService;

    @GetMapping("/categories/{categoryId}")
    public String list(@PathVariable Long categoryId, Model model) {
        CategoryDto category = categoryQueryService.getCategory(categoryId);
        model.addAttribute("category", category);
        return "catalog/products";
    }

    @GetMapping("/products/{productId}")
    public String detail(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long productId, Model model) {
        Long userId = user != null ? user.getId() : null;
        ProductDto product = productQueryService.getProduct(userId, productId);
        model.addAttribute("product", product);
        return "catalog/product";
    }
}
