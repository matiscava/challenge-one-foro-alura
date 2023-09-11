package com.alura.foro.dto.topico;

import com.alura.foro.modelo.Topico;

import java.time.LocalDateTime;

public record TopicoRespuestaRegistroDto(Long id, String titulo, LocalDateTime fecha) {
    public TopicoRespuestaRegistroDto(Topico topico) {
        this(topico.getId(),topico.getTitulo(),topico.getFechaCreacion());
    }
}
