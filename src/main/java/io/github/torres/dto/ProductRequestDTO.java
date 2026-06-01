package io.github.torres.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequestDTO(

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 100, message = "El nombre no puede exeder los 100 caracteres")
    String name,

    @Size(max = 225, message = "La descripción no puede exceder los 255 caracteres")
    String description,

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    BigDecimal price,

    @PositiveOrZero(message = "El stock no puede ser negativo")
    int stock
){}

