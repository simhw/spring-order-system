package com.example.shop.myproject.catalog.command.domain.product;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Lock(LockModeType.OPTIMISTIC)
    @Query(value = "select p from Product p where p.id = :id")
    Optional<Product> findByIdForUpdate(Long id);

    @Query("""
                select distinct p
                from Product p
                join ProductCategory pc on p.id = pc.product.id
                where pc.category.id = :categoryId
            """)
    Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
}
