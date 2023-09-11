package com.alura.foro.dto.curso;

import com.alura.foro.modelo.Curso;

public record CursoRespuestaDto(Long id, String nombre, String categoria) {
    public CursoRespuestaDto(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
