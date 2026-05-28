package io.github.torres.service;

import io.github.torres.dto.ProductRequestDTO;
import io.github.torres.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponseDTO> getAllProducts(Pageable pageable);
    ProductResponseDTO createProduct(ProductRequestDTO requestDTO);
    ProductResponseDTO updateProduct(Long id,ProductRequestDTO requestDTO);
    ProductResponseDTO getProductById(Long id);
    void deleteProduct(Long id);
}
