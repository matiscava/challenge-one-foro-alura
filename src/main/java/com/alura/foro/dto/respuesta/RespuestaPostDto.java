package com.alura.foro.dto.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RespuestaPostDto(
        @NotNull
        @NotBlank
        String mensaje,
        @NotNull
        Long topico_id,
        @NotNull
        Long autor_id
        ) {
}
