package com.example.shop.myproject.catalog.ui;


import com.example.shop.myproject.auth.UserDetailsImpl;
import com.example.shop.myproject.catalog.query.product.application.ProductListView;
import com.example.shop.myproject.catalog.query.product.application.ProductQueryServiceV2;
import com.example.shop.myproject.catalog.query.product.dto.ProductDto;
import com.example.shop.myproject.common.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v2")
@RestController
public class ProductRestControllerV2 {

    private final ProductQueryServiceV2 productQueryService;

    @GetMapping("/categories/{categoryId}/products")
    public ApiResponse<ProductListView> list(@PathVariable Long categoryId, Pageable pageable) {
        try {
            ProductListView data = productQueryService.getProductInCategory(categoryId, pageable);
            return new ApiResponse<>(HttpStatus.OK, data);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/products/{productId}")
    public ApiResponse<ProductDto> detail(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long productId) {
        try {
            Long userId = user != null ? user.getId() : null;
            ProductDto data = productQueryService.getProduct(userId, productId);
            return new ApiResponse<>(HttpStatus.OK, data);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}

