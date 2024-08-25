package com.example.shop.myproject.catalog.query.product;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final EntityManager em;

    public List<Product> findAllInCategoryIds(List<Long> categoryIds) {
        String jpql = "select p " +
                "from Product p " +
                "left join fetch p.category c " +
                "where c.parent.id = in (:categoryIds) ";

        return em.createQuery(jpql, Product.class)
                .setParameter("categoryIds", categoryIds)
                .getResultList();
    }
}
