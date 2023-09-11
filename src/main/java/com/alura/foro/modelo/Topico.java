package com.alura.foro.modelo;

import com.alura.foro.dto.topico.TopicoPostDto;
import com.alura.foro.dto.topico.TopicoPutDto;
import com.alura.foro.errores.ValidacionRechazada;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Table(name="topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id")
	private Curso curso;

	public Topico(TopicoPostDto topicoPostDto, Usuario autor, Curso curso) {
		this.titulo = topicoPostDto.titulo();
		this.mensaje = topicoPostDto.mensaje();
		this.autor = autor;
		this.curso = curso;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void actualizaDatos(TopicoPutDto topicoPutDto) {
		if(topicoPutDto.titulo() != null) {
			this.titulo = topicoPutDto.titulo();
		}
		if(topicoPutDto.status() != null) {
			this.status = StatusTopico.valueOf(topicoPutDto.status());
		}
		if(topicoPutDto.mensaje() != null) {
			this.mensaje = topicoPutDto.mensaje();
		}
	}
}
