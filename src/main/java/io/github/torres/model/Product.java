package io.github.torres.model;

import jakarta.persistence.*;

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
    private Integer id;

    // 2. Define the name colum (cannot be null)
    @Column(nullable = false, length = 100)
    private String name;
    
    // 3. Define the description field
    @Column(length = 255)
    private String description;

    // 4. Define the price column (cannot be null)
    @Column(nullable = false)
    private Double price;

    // 5. Define the stock column 
    @Column (nullable = false)
    private Integer stock;

    // 6. Generete empty constructor (Required by Spring/JPA/Hibernate)
    public Product(){

    }

    // 7. Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
}
