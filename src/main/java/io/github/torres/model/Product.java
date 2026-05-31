package io.github.torres.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
/**
 * Represents a product entity in the inventory system.
 * Mapped to the 'products' table in the database
 */
@Entity
@Table(name="products")
public class Product {

    // 1. Mark this field as the Primary Key and set it to Auto-Incerement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable=false,nullable=false)
    private Long id;

    // 2. Define the name colum (cannot be null)
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
    private Integer stock;

    // 6. Generete empty constructor (Required by Spring/JPA/Hibernate)
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
