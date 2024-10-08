package com.example.shop.myproject.catalog.query.product.application;

import com.example.shop.myproject.catalog.command.application.NoProductException;
import com.example.shop.myproject.catalog.command.domain.category.Category;
import com.example.shop.myproject.catalog.command.domain.category.CategoryRepository;
import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.catalog.query.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductQueryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    /**
     * 최하단의 자식 카테고리를 조회하여, 해당 카테고리의 모든 상품 조회
     *
     * @param categoryId 카테고리 아이디
     * @param pageable   페이지
     * @return
     */
    @Deprecated
    @Transactional(readOnly = true)
    public ProductListView getProductInCategory(Long categoryId, Pageable pageable) {
        List<Long> leafCategoryIds = getLeafCategoryIds(categoryId);
        List<Category> categories = categoryRepository.findByIdIn(leafCategoryIds);
        Page<Product> products = new PageImpl<>(new ArrayList<>());

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

    /**
     * 최하단의 카테고리 아이디 조회
     *
     * @param categoryId 카테고리 아이디
     * @return 카테고리 아이디 리스트
     */
    public List<Long> getLeafCategoryIds(Long categoryId) {
        List<Long> leafCategoryIds = new ArrayList<>();
        List<Long> childrenIds = categoryRepository.findChildrenById(categoryId).stream()
                .map(Category::getId)
                .toList();

        if (childrenIds.isEmpty()) {
            leafCategoryIds.add(categoryId);
            return leafCategoryIds;
        }

        for (Long childrenId : childrenIds) {
            leafCategoryIds.addAll(getLeafCategoryIds(childrenId));
        }
        return leafCategoryIds;
    }

}