package com.alura.foro.service;

import com.alura.foro.dto.respuesta.RespuestaEditDto;
import com.alura.foro.dto.respuesta.RespuestaPostDto;
import com.alura.foro.dto.respuesta.RespuestaRespuestaDto;
import com.alura.foro.errores.ValidacionRechazada;
import com.alura.foro.modelo.Respuesta;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.IRespuestaRepository;
import com.alura.foro.repository.ITopicoRepository;
import com.alura.foro.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RespuestaSevice {
    private final IRespuestaRepository respuestaRepository;
    private final ITopicoRepository topicoRepository;
    private final IUsuarioRepository usuarioRepository;

    public RespuestaSevice(IRespuestaRepository respuestaRepository, ITopicoRepository topicoRepository, IUsuarioRepository usuarioRepository) {
        this.respuestaRepository = respuestaRepository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public RespuestaRespuestaDto registrar(RespuestaPostDto respuestaPostDto){
        if(!topicoRepository.findById(respuestaPostDto.topico_id()).isPresent()){
            throw new ValidacionRechazada("No existe el topico con ese id");
        }
        if(!usuarioRepository.existsById(respuestaPostDto.autor_id())){
            throw new ValidacionRechazada("No existe el autor con ese id");
        }

        Topico topico = topicoRepository.getReferenceById(respuestaPostDto.topico_id());
        Usuario usuario = usuarioRepository.getReferenceById(respuestaPostDto.topico_id());

        Respuesta respuesta = new Respuesta(respuestaPostDto,topico,usuario);

        respuestaRepository.save(respuesta);
        return new RespuestaRespuestaDto(respuesta);
    }

    public List<RespuestaRespuestaDto> getAll() {
        List<Respuesta> respuestaList = respuestaRepository.findAll();
        List<RespuestaRespuestaDto> respuestaDtos = new ArrayList<>();
        for (Respuesta r:
        respuestaList){
            respuestaDtos.add( new RespuestaRespuestaDto(r));
        }
        return respuestaDtos;
    }

    public RespuestaRespuestaDto getById(Long id) {
        if(!respuestaRepository.existsById(id)){
            throw new ValidacionRechazada("No existe la Respuesta con ese id");
        }
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        return new RespuestaRespuestaDto(respuesta);
    }

    public RespuestaRespuestaDto editar(Long id, RespuestaEditDto respuestaEditDto) {
        if(!respuestaRepository.existsById(id)){
            throw new ValidacionRechazada("No existe la Respuesta con ese id");
        }
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarDatos(respuestaEditDto);
        return new RespuestaRespuestaDto(respuesta);
    }

    public void eliminar(Long id) {
        if(!respuestaRepository.existsById(id)){
            throw new ValidacionRechazada("No existe la Respuesta con ese id");
        }
        respuestaRepository.deleteById(id);
    }
}
