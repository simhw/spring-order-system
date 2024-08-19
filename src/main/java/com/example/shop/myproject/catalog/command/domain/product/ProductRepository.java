package com.example.shop.myproject.catalog.command.domain.product;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Lock(LockModeType.OPTIMISTIC)
    @Query(value = "select p from Product p where p.id = :id")
    Optional<Product> findByIdForUpdate(Long id);
}
