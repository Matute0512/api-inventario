package io.github.torres.repository;

import io.github.torres.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) for the Product entity.
 * Extends JpaRepository to inherit standard CRUD operations automatically
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
