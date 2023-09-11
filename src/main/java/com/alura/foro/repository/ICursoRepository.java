package com.alura.foro.repository;

import com.alura.foro.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepository extends JpaRepository<Curso,Long> {
}
