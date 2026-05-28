package io.github.torres.service;

import io.github.torres.dto.ProductRequestDTO;
import io.github.torres.dto.ProductResponseDTO;
import io.github.torres.model.Product;
import io.github.torres.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    // Dependency injection by constructor
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts(){
        // We obtain the entities, map them to DTOs using Streams, and return them
        return productRepository.findAll().stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO){
        // Manual mapping of DTO to Entity
        Product product = new Product();
        product.setName(requestDTO.name());
        product.setDescription(requestDTO.description());
        product.setPrice(requestDTO.price());
        product.setStock(requestDTO.stock());

        // Save to the database
        Product savedProduct = productRepository.save(product);

        // Return the DTO
        return mapToResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO){
        // We are looking for the product.
        Product existingProduct = productRepository.findById(id).orElseThrow();

        // We updated the data
        existingProduct.setName(requestDTO.name());
        existingProduct.setDescription(requestDTO.description());
        existingProduct.setPrice(requestDTO.price());
        existingProduct.setStock(requestDTO.stock());

        // Save and return the DTO
        Product updatedProduct = productRepository.save(existingProduct);
        return  mapToResponseDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow();
        return mapToResponseDTO(product);
    }

    // Private helper method (DRY - Don't Repeat Yourself) to map Entity to Response DTO
    private ProductResponseDTO mapToResponseDTO(Product product){
        return  new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}
