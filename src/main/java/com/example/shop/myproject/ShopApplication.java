package com.example.shop.myproject;


import com.example.shop.myproject.catalog.command.domain.category.Category;
import com.example.shop.myproject.catalog.command.domain.category.CategoryRepository;
import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class ShopApplication {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    ShopApplication(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @PostConstruct
    public void init() {
         //category();
    }

    public void category() {
        List<Category> categories = new ArrayList<>();

        // dep1
        Category women = new Category("WOMEN", null, 1);
        Category men = new Category("MEN", null, 1);
        Category interior = new Category("INTERIOR", null, 1);

        // dep2
        Category clo1 = new Category("의류", women, 2);
        Category bag1 = new Category("가방", women, 2);

        Category clo2 = new Category("의류", men, 2);
        Category bag2 = new Category("가방", men, 2);
        Category bed = new Category("침구", interior, 2);

        // dep3
        Category top1 = new Category("상의", clo1, 3);
        Category bottom1 = new Category("하의", clo1, 3);
        Category outer1 = new Category("아우터", clo1, 3);

        Category top2 = new Category("상의", clo2, 3);
        Category bottom2 = new Category("하의", clo2, 3);
        Category outer2 = new Category("아우터", clo2, 3);

        categoryRepository.saveAll(List.of(
                women, men, interior,
                clo1, bag1, bag2, clo2, bag2, bed,
                top1, bottom1, outer1, top2, bottom2, outer2
        ));

        Product product1 = new Product("여성 > 의류 > 상의1", 10000, 10, "상세설명입니다.", top1);
        Product product2 = new Product("여성 > 의류 > 상의2", 10000, 10, "상세설명입니다.", top1);
        Product product3 = new Product("여성 > 의류 > 상의3", 3100, 10, "상세설명입니다.", top1);

        Product product4 = new Product("남성 > 의류 > 상의1", 10000, 10, "상세설명입니다.", top2);
        Product product5 = new Product("남성 > 의류 > 상의2", 10000, 10, "상세설명입니다.", top2);
        Product product6 = new Product("남성 > 의류 > 상의3", 3100, 10, "상세설명입니다.", top2);

        Product product7 = new Product("여성 > 의류 > 하의1", 10000, 10, "상세설명입니다.", bottom1);
        Product product8 = new Product("여성 > 의류 > 하의2", 10000, 10, "상세설명입니다.", bottom1);
        Product product9 = new Product("여성 > 의류 > 하의3", 3100, 10, "상세설명입니다.", bottom1);

        Product product10 = new Product("남성 > 의류 > 하의1", 10000, 10, "상세설명입니다.", bottom2);
        Product product11 = new Product("남성 > 의류 > 하의2", 10000, 10, "상세설명입니다.", bottom2);
        Product product12 = new Product("남성 > 의류 > 하의3", 3100, 10, "상세설명입니다.", bottom2);

        Product product13 = new Product("여성 > 의류 > 아우터1", 10000, 10, "상세설명입니다.", outer1);
        Product product14 = new Product("여성 > 의류 > 아우터2", 10000, 10, "상세설명입니다.", outer1);
        Product product15 = new Product("여성 > 의류 > 아우터3", 3100, 10, "상세설명입니다.", outer1);

        Product product16 = new Product("남성 > 의류 > 아우터1", 10000, 10, "상세설명입니다.", outer2);
        Product product17 = new Product("남성 > 의류 > 아우터2", 10000, 10, "상세설명입니다.", outer2);
        Product product18 = new Product("남성 > 의류 > 아우터3", 3100, 10, "상세설명입니다.", outer2);

        productRepository.saveAll(List.of(
                product1, product2, product3,
                product4, product5, product6,
                product7, product8, product9,
                product10, product11, product12,
                product13, product14, product15,
                product16, product17, product18
        ));

    }

}
