package com.alura.foro.repository;

import com.alura.foro.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico,Long> {
    Boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
