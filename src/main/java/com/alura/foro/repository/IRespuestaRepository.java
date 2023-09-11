package com.alura.foro.repository;

import com.alura.foro.modelo.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRespuestaRepository extends JpaRepository<Respuesta, Long> {
}
