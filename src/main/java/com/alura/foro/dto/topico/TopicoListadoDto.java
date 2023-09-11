package com.alura.foro.dto.topico;

import com.alura.foro.modelo.Topico;

import java.time.LocalDateTime;

public record TopicoListadoDto(String titulo, String mensaje, LocalDateTime fechaCreacion, String status, String curso_nombre) {

    public TopicoListadoDto(Topico t) {
        this(t.getTitulo(),t.getMensaje(),t.getFechaCreacion(),t.getStatus().name(),t.getCurso().getNombre());
    }
}
