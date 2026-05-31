package io.github.torres.mapper;

import io.github.torres.dto.ProductRequestDTO;
import io.github.torres.dto.ProductResponseDTO;
import io.github.torres.model.Product;

public final class ProductMapper {

    private ProductMapper() {}

    public static Product toEntity(ProductRequestDTO dto){
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());

        return product;
    }
    public static ProductResponseDTO toResponseDTO(Product product){
        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }
}
