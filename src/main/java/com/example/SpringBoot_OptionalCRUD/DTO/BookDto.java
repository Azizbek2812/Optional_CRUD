package com.example.SpringBoot_OptionalCRUD.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record BookDto (
    @NotBlank(message = "Title cannot be empty")
    String title,

    @NotBlank(message = "Author cannot be empty")
    String author,

    String description,

    @NotNull(message = "Price cannot be null")
    Double price
)
{}
