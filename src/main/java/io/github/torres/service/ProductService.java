package io.github.torres.service;

import io.github.torres.dto.ProductRequestDTO;
import io.github.torres.dto.ProductResponseDTO;
import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO createProduct(ProductRequestDTO requestDTO);
    ProductResponseDTO updateProduct(Long id,ProductRequestDTO requestDTO);
    ProductResponseDTO getProductById(Long id);
    void deleteProduct(Long id);
}
