package io.github.torres.service;

import io.github.torres.dto.ProductRequestDTO;
import io.github.torres.dto.ProductResponseDTO;
import io.github.torres.exception.ProductNotFoundException;
import io.github.torres.model.Product;
import io.github.torres.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    // @Mock creates a fake "bouble" of the repository. It will not connect to the database.
    @Mock
    private ProductRepository productRepository;

    // @InjectMocks injects the fake repository into our real service instance.
    @InjectMocks
    private ProductServiceImpl productService;

    private Product productEntity;
    private ProductRequestDTO requestDTO;

    // @BeforeEach runs BEFORE each test to initialize clean data.
    @BeforeEach
    public void setUp() {
        productEntity = new Product();
        productEntity.setId(1L);
        productEntity.setName("Teclado Mecánico");
        productEntity.setDescription("Switches Red");
        productEntity.setPrice(new BigDecimal("100.00"));
        productEntity.setStock(50);

        requestDTO = new ProductRequestDTO(
                "Teclado Mecánico",
                "Switches Red",
                new BigDecimal("100.00"),
                50
        );
    }

    @Test
    void getProductById_Success() {
        // 1. Arrange: Tell the Mock what to return when called.
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));

        // 2. Act: Call the real method of our service.
        ProductResponseDTO response = productService.getProductById(1L);

        // 3. Assert: Verify that the result is as expected.
        assertNotNull(response);
        assertEquals("Teclado Mecánico", response.name());

        // Verify that the service called the repository exactly once.
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void getProductById_NotFound_ThrowsException() {
        // Arrange: Simulate that the database returns an empty result (found nothing).
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert: Verify that it throws exactly our ProductNotFoundException.
        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(99L);
        });

        verify(productRepository, times(1)).findById(99L);
    }

    @Test
    void createProduct_Success() {
        // Arrange: Simulate that when performing a "save", it returns the entity with the generated ID.
        when(productRepository.save(any(Product.class))).thenReturn(productEntity);

        // Act
        ProductResponseDTO response = productService.createProduct(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.id()); // The service must map the entity's ID to the DTO
        assertEquals("Teclado Mecánico", response.name());

        verify(productRepository, times(1)).save(any(Product.class));
    }
}
