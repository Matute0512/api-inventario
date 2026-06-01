package io.github.torres.model;

import jakarta.persistence.*;
import jakarta.persistence.Index;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a product in the inventory system.
 * Audit fields (createdAt, updatedAt) are managed automatically
 * by Spring Data JPA Auditing — do not set them manually.
 */
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="products",indexes = {@Index(name="idx_product_name",columnList = "name")})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable=false,nullable=false)
    private Long id;

    @Column(name="name",nullable = false, length = 100)
    private String name;

    @Column(name="description",length = 255)
    private String description;

    @Column(name="price",nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column (name= "stock",nullable = false)
    private int stock;

    // --- Audit fields ---
    @CreatedDate
    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    public Product(){
    }

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
