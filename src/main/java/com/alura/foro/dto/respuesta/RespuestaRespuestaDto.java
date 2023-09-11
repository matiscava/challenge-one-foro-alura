package com.alura.foro.dto.respuesta;

import com.alura.foro.modelo.Respuesta;

import java.time.LocalDateTime;

public record RespuestaRespuestaDto(String mensaje, String topico, String autor, LocalDateTime fechaCreacion) {
    public RespuestaRespuestaDto(Respuesta respuesta){
        this(
                respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getAutor().getNombre(),
                respuesta.getFechaCreacion()
        );
    }
}
