package com.alura.foro.dto.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoPostDto(
        @NotBlank
        @NotNull
        String nombre,
        @NotBlank
        @NotNull
        String categoria
) {
}
