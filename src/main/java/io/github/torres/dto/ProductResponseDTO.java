package io.github.torres.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO representing the product data returned to the client.
 * Includes audit fields (createdAt, updatedAt) managed by Spring Data JPA Auditing.
 */
public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        int stock,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
