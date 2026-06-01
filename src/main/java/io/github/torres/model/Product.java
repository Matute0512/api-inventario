package io.github.torres.model;

import jakarta.persistence.*;
import jakarta.persistence.Index;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a product entity in the inventory system.
 * Mapped to the 'products' table in the database
 */
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="products",indexes = {@Index(name="idx_product_name",columnList = "name")})
public class Product {

    // 1. Mark this field as the Primary Key and set it to Auto-Increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable=false,nullable=false)
    private Long id;

    // 2. Define the name column (cannot be null)
    @Column(name="name",nullable = false, length = 100)
    private String name;
    
    // 3. Define the description field
    @Column(name="description",length = 255)
    private String description;

    // 4. Define the price column (cannot be null)
    @Column(name="price",nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // 5. Define the stock column 
    @Column (name= "stock",nullable = false)
    private int stock;

    // --- Audit fields ---
    @CreatedDate
    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    // 6. Generate empty constructor (Required by Spring/JPA/Hibernate)
    public Product(){
    }

    // 7. Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
