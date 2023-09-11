package com.alura.foro.dto.topico;

import com.alura.foro.modelo.Topico;

import java.time.LocalDateTime;

public record TopicoGetByIdDto(String titulo, String mensaje, LocalDateTime fecha_de_creacion, String status, String autor, String curso) {

    public TopicoGetByIdDto(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),topico.getStatus().name(),topico.getAutor().getEmail(),topico.getCurso().getNombre());
    }
}
