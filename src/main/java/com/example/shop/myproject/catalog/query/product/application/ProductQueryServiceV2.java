package com.example.shop.myproject.catalog.query.product.application;

import com.example.shop.myproject.catalog.command.application.NoProductException;
import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.catalog.query.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductQueryServiceV2 {

    private final ProductRepository productRepository;

    /**
     * 최하단의 자식 카테고리를 조회하여, 해당 카테고리의 모든 상품 조회
     *
     * @param categoryId 카테고리 아이디
     * @param pageable   페이지
     * @return ProductListView
     */
    @Transactional(readOnly = true)
    public ProductListView getProductInCategory(Long categoryId, Pageable pageable) {
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageable);
        List<ProductSummary> summaries = products.stream()
                .map(ProductSummary::new)
                .toList();

        return new ProductListView(
                summaries,
                products.getTotalElements(),
                products.getTotalPages()
        );
    }

    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NoProductException::new);
        return new ProductDto(product);
    }
}