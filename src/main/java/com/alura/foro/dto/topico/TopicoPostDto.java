package com.alura.foro.dto.topico;

import jakarta.validation.constraints.NotNull;

public record TopicoPostDto(@NotNull String titulo,@NotNull String mensaje,@NotNull Long autor_id,@NotNull Long curso_id) {
}
