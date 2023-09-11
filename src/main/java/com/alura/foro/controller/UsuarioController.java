package com.alura.foro.controller;

import com.alura.foro.dto.usuario.UsuarioPostDto;
import com.alura.foro.dto.usuario.UsuarioPutDto;
import com.alura.foro.dto.usuario.UsuarioRespuestaDto;
import com.alura.foro.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@ResponseBody
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioRespuestaDto> registrarUsuario (@RequestBody @Valid UsuarioPostDto usuarioPostDto){
        UsuarioRespuestaDto respuesta = usuarioService.registrar(usuarioPostDto);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity listarUsuarios() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDto> listarUsuarioPorId(@PathVariable Long id){
        UsuarioRespuestaDto respuesta = usuarioService.getById(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioRespuestaDto> editarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioPutDto usuarioPutDto) {
        return ResponseEntity.ok(usuarioService.editar(id, usuarioPutDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
