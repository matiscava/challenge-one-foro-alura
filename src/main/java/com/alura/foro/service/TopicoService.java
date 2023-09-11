package com.alura.foro.service;

import com.alura.foro.dto.topico.*;
import com.alura.foro.errores.ValidacionRechazada;
import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.ICursoRepository;
import com.alura.foro.repository.ITopicoRepository;
import com.alura.foro.repository.IUsuarioRepository;
import com.alura.foro.infra.validar.statusTopico.StatusNoExiste;
import com.alura.foro.infra.validar.topico.TopicoRepetido;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicoService {
    private final ITopicoRepository topicoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ICursoRepository cursoRepository;
    final TopicoRepetido topicoRepetido;
    final StatusNoExiste statusNoExiste;

    public TopicoService(
            ITopicoRepository topicoRepository,
            IUsuarioRepository usuarioRepository,
            ICursoRepository cursoRepository,
            TopicoRepetido topicoRepetido,
            StatusNoExiste statusNoExiste) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.topicoRepetido = topicoRepetido;
        this.statusNoExiste = statusNoExiste;
    }


    public TopicoRespuestaRegistroDto registrar(TopicoPostDto topicoPostDto) {
        //Validacion
        topicoRepetido.validar(topicoPostDto);

        Usuario autor = usuarioRepository.findById(topicoPostDto.autor_id()).get();
        Curso curso = cursoRepository.findById(topicoPostDto.curso_id()).get();

        Topico topico = new Topico(topicoPostDto,autor,curso);

        topicoRepository.save(topico);

        return new TopicoRespuestaRegistroDto(topico);
    }

    public List<TopicoListadoDto> getAll() {
        List<Topico> topicoList = topicoRepository.findAll();
        List<TopicoListadoDto> topicoListadoDtoList = new ArrayList<>();
        for (Topico t :
                topicoList) {
            topicoListadoDtoList.add(new TopicoListadoDto(t));
        }
        return topicoListadoDtoList;
    }

    public TopicoGetByIdDto getById(Long id) {
        if(!topicoRepository.existsById(id)){
            throw new ValidacionRechazada("No existe el Tópico con ese id");
        }
        Topico topico = topicoRepository.getReferenceById(id);
        return new TopicoGetByIdDto(topico);
    }

    public TopicoRespuestaRegistroDto editar(Long id,TopicoPutDto topicoPutDto) {
        //Validacion
        if(!topicoRepository.existsById(id)){
            throw new ValidacionRechazada("No existe el Tópico con ese id");
        }
        statusNoExiste.validar(topicoPutDto.status());
        //update
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizaDatos(topicoPutDto);
        topicoRepository.save(topico);
        return new TopicoRespuestaRegistroDto(topico);
    }

    public void eliminar(Long id) {
        if(!topicoRepository.existsById(id)){
            throw new ValidacionRechazada("No existe el Tópico con ese id");
        }
        topicoRepository.deleteById(id);
    }
}
