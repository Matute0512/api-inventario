package io.github.torres.service;

import io.github.torres.dto.ProductRequestDTO;
import io.github.torres.dto.ProductResponseDTO;
import io.github.torres.exception.ProductNotFoundException;
import io.github.torres.model.Product;
import io.github.torres.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    // Dependency injection by constructor
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts(){
        logger.info("Buscando el listado completo de productos en la base de datos...");
        // We obtain the entities, map them to DTOs using Streams, and return them
        return productRepository.findAll().stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO){
        logger.info("Iniciando la creacion del producto: {}",requestDTO.name());

        // Manual mapping of DTO to Entity
        Product product = new Product();
        product.setName(requestDTO.name());
        product.setDescription(requestDTO.description());
        product.setPrice(requestDTO.price());
        product.setStock(requestDTO.stock());

        // Save to the database
        Product savedProduct = productRepository.save(product);
        logger.info("Producto creado con ID asignado: {}", savedProduct.getId());

        // Return the DTO
        return mapToResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO){
        logger.info("Iniciando la actualización del producto con ID: {}",id);

        // We are looking for the product.
        Product existingProduct = productRepository.findById(id).orElseThrow(() ->{
            logger.error("Error al actualizar: No existe el producto con ID: {}",id);
            return new ProductNotFoundException("No se puede actualizar. El producto con el ID: "+id+" no existe.");
        });

        // We updated the data
        existingProduct.setName(requestDTO.name());
        existingProduct.setDescription(requestDTO.description());
        existingProduct.setPrice(requestDTO.price());
        existingProduct.setStock(requestDTO.stock());

        // Save and return the DTO
        Product updatedProduct = productRepository.save(existingProduct);
        logger.info("Producto con ID: {} actualizado correctamente.",updatedProduct.getId());
        return  mapToResponseDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id){
        logger.warn("Solicitud para eliminar el producto con ID: {}",id);
        if(!productRepository.existsById(id)){
            logger.error("Error al eliminar el producto con ID: {}",id);
            throw new ProductNotFoundException("No se puede eliminar. El producto con el ID: "+id+" no existe.");
        }
        productRepository.deleteById(id);
        logger.info("Producto con ID: {} eliminado correctamente.",id);
    }

    @Override
    public ProductResponseDTO getProductById(Long id){
        logger.info("Buscando producto con ID: {}",id);
        Product product = productRepository.findById(id).orElseThrow(()->{
            logger.error("Error: No se encontró el producto con ID: {}",id);
            return new ProductNotFoundException("El producto con el ID: "+id+" no existe.");
        });
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
