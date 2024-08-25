package com.example.shop.myproject.catalog.query.product;

import com.example.shop.myproject.catalog.command.domain.category.Category;
import com.example.shop.myproject.catalog.command.domain.category.CategoryRepository;
import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.catalog.query.category.CategorySummary;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductQueryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    // category
    public List<CategorySummary> getAllCategory() {
        return categoryRepository.findByDepth(1).stream()
                .map(CategorySummary::new).collect(Collectors.toList());
    }

    /**
     * 최하단의 자식 카테고리를 조회하여, 해당 카테고리의 모든 상품 조회
     *
     * @param categoryId
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public CategoryProduct getProductInCategory(Long categoryId, Pageable pageable) {
        List<Long> leafCategoryIds = getLeafCategoryIds(categoryId);
        List<Category> categories = categoryRepository.findByIdIn(leafCategoryIds);
        Page<Product> products = productRepository.findByCategoryIn(categories, pageable);

        List<ProductSummary> summaries = products.stream()
                .map(ProductSummary::new)
                .toList();

        return new CategoryProduct(
                summaries,
                products.getTotalElements(),
                products.getTotalPages()
        );
    }

    /**
     * 최하단의 카테고리 아이디 조회
     *
     * @param categoryId
     * @return
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