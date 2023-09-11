package com.alura.foro.infra.validar.topico;

import com.alura.foro.dto.topico.TopicoPostDto;
import com.alura.foro.errores.ValidacionRechazada;
import com.alura.foro.repository.ITopicoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class TopicoRepetido {
    public final ITopicoRepository topicoRepository;

    public TopicoRepetido(ITopicoRepository topicoRepository){
        this.topicoRepository = topicoRepository;
    }

    public void validar(@NotNull TopicoPostDto datos) {
        Boolean topicoRepetido = topicoRepository.existsByTituloAndMensaje(datos.titulo(),datos.mensaje());

        if(topicoRepetido){
            throw new ValidacionRechazada("Ya existe un tópico con ese título y mensaje.");
        }
    }
}
