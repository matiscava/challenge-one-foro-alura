package com.alura.foro.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioLoginDto(
        @NotNull
        @NotBlank
        @Email
        String email,
        @NotNull
        @NotBlank
        String contrasena
) {
}
