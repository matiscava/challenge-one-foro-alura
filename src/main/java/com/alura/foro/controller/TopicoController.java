package com.alura.foro.controller;

import com.alura.foro.dto.topico.TopicoGetByIdDto;
import com.alura.foro.dto.topico.TopicoPostDto;
import com.alura.foro.dto.topico.TopicoPutDto;
import com.alura.foro.dto.topico.TopicoRespuestaRegistroDto;
import com.alura.foro.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@ResponseBody
@RequestMapping( "/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private TopicoService topicoService;

    public TopicoController(TopicoService topicoService){
        this.topicoService = topicoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoRespuestaRegistroDto> registrarTopico (@RequestBody @Valid TopicoPostDto topicoPostDto, UriComponentsBuilder uriComponentsBuilder) {
        TopicoRespuestaRegistroDto respuesta = topicoService.registrar(topicoPostDto);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity listarTopicos(){
        return ResponseEntity.ok(topicoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoGetByIdDto> listarCursoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.getById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoRespuestaRegistroDto> editarTopico(@PathVariable Long id, @RequestBody TopicoPutDto topicoPutDto){
        return ResponseEntity.ok(topicoService.editar(id,topicoPutDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
