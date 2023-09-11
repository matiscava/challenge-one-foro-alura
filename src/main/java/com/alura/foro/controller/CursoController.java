package com.alura.foro.controller;

import com.alura.foro.dto.curso.CursoPostDto;
import com.alura.foro.dto.curso.CursoPutDto;
import com.alura.foro.dto.curso.CursoRespuestaDto;
import com.alura.foro.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CursoRespuestaDto> registrarCurso (@RequestBody @Valid CursoPostDto cursoPostDto){
        CursoRespuestaDto respuesta = cursoService.registrar(cursoPostDto);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<CursoRespuestaDto>> listarCursos() {
        return ResponseEntity.ok(cursoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoRespuestaDto> listarCursoPorId(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.getById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CursoRespuestaDto> editarCurso(@PathVariable Long id, @RequestBody CursoPutDto cursoPutDto) {
        return ResponseEntity.ok(cursoService.editar(id,cursoPutDto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
