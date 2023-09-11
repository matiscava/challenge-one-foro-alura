package com.alura.foro.service;

import com.alura.foro.dto.curso.CursoPostDto;
import com.alura.foro.dto.curso.CursoPutDto;
import com.alura.foro.dto.curso.CursoRespuestaDto;
import com.alura.foro.errores.ValidacionRechazada;
import com.alura.foro.modelo.Curso;
import com.alura.foro.repository.ICursoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {
    ICursoRepository cursoRepository;

    public CursoService(ICursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public CursoRespuestaDto registrar(CursoPostDto cursoPostDto){
        Curso curso = new Curso(cursoPostDto);
        cursoRepository.save(curso);
        return new CursoRespuestaDto(curso);
    }

    public List<CursoRespuestaDto> getAll() {
        List<Curso> cursoList = cursoRepository.findAll();
        List<CursoRespuestaDto> cursoRespuestaDtoList = new ArrayList<>();
        for ( Curso curso :
            cursoList) {
            cursoRespuestaDtoList.add(new CursoRespuestaDto(curso));
        }
        return cursoRespuestaDtoList;
    }

    public CursoRespuestaDto getById(Long id) {
        if(!cursoRepository.existsById(id)){
            throw new ValidacionRechazada("No existe el curso con ese Id");
        }
        Curso curso = cursoRepository.getReferenceById(id);
        return new CursoRespuestaDto(curso);
    }

    public CursoRespuestaDto editar(Long id, CursoPutDto cursoPutDto) {
        if(!cursoRepository.existsById(id)){
            throw new ValidacionRechazada("No existe el curso con ese Id");
        }
        Curso curso = cursoRepository.getReferenceById(id);
        curso.actualizarDatos(cursoPutDto);
        cursoRepository.save(curso);
        return new CursoRespuestaDto(curso);
    }

    public void eliminar(Long id) {
        if(!cursoRepository.existsById(id)){
            throw new ValidacionRechazada("No existe el curso con ese Id");
        }

        cursoRepository.deleteById(id);
    }
}
