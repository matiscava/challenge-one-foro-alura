package com.alura.foro.dto.usuario;

import com.alura.foro.modelo.Usuario;

public record UsuarioRespuestaDto(Long id,String nombre, String email) {
    public UsuarioRespuestaDto(Usuario usuario) {
        this(usuario.getId(),usuario.getNombre(),usuario.getEmail());
    }
}
