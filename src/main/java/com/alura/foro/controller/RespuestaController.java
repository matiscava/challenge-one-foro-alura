package com.alura.foro.controller;

import com.alura.foro.dto.respuesta.RespuestaEditDto;
import com.alura.foro.dto.respuesta.RespuestaPostDto;
import com.alura.foro.dto.respuesta.RespuestaRespuestaDto;
import com.alura.foro.service.RespuestaSevice;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    private final RespuestaSevice respuestaSevice;

    public RespuestaController(RespuestaSevice respuestaSevice) {
        this.respuestaSevice = respuestaSevice;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaRespuestaDto> registrarRespuesta (@RequestBody @Valid RespuestaPostDto respuestaPostDto){
        RespuestaRespuestaDto respuesta = respuestaSevice.registrar(respuestaPostDto);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<RespuestaRespuestaDto>> listarCursos() {
        return ResponseEntity.ok(respuestaSevice.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaRespuestaDto> listarRespuestaPorId(@PathVariable Long id){
        return ResponseEntity.ok(respuestaSevice.getById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaRespuestaDto> editarRespuesta(@PathVariable Long id, @RequestBody RespuestaEditDto respuestaEditDto){
        return ResponseEntity.ok(respuestaSevice.editar(id,respuestaEditDto));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaSevice.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
