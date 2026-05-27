package io.github.torres.controller;

import io.github.torres.model.Product;
import io.github.torres.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing products.
 * Acts as the entry point for HTTP requests.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Dependency Injection: Spring automatically provides the repository instance
    @Autowired
    private ProductRepository productRepository;

    // 1. GET: Retrieve all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. POST: Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // @RequestBody tells Spring to convert the incoming JSON into a Product
        // objectapiInventarioApplication
        return productRepository.save(product);
    }

    // 3. DELETE: Remove a product by ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        // @PathVariable extracts the ID from the URL (e.g., /api/products/5)
        productRepository.deleteById(id);
    }

    // 4. PUT: Update an existing product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {

        if (product != null && id > 0) {
            // Ratrieve the original product from the database (throws an exception if not
            // found)
            Product existingProduct = productRepository.findById(id).orElseThrow();

            // Overwrite the old data with the new data received via JSON
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());

            // Save the update product (Spring performs an UPDATE because the ID already
            // exists)
            return productRepository.save(existingProduct);

        }

        // If validation fails, throws an exception
        throw new IllegalArgumentException("Datos o ID de producto no v");
    }
}
