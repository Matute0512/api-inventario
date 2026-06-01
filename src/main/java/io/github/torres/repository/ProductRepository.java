package io.github.torres.repository;

import io.github.torres.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Data Access Object (DAO) for the Product entity.
 * Extends JpaRepository to inherit standard CRUD operations automatically.
 * Extends JpaSpecificationExecutor to support dynamic filtering via Specifications.
 */
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // Derived query - Spring Data generates the SQL automatically
    // Useful for checking duplicate names before creating or updating a product
    Optional<Product> findByNameIgnoreCase(String name);

    // Derived query - filters products with available stock
    Page<Product> findByStockGreaterThan(int minStock, Pageable pageable);

}
