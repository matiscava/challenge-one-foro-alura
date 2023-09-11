package com.alura.foro.modelo;

import com.alura.foro.dto.curso.CursoPostDto;
import com.alura.foro.dto.curso.CursoPutDto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String categoria;

	public Curso(CursoPostDto cursoPostDto) {
		this.nombre = cursoPostDto.nombre();
		this.categoria = cursoPostDto.categoria();
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
		Curso other = (Curso) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}

	public void actualizarDatos(CursoPutDto cursoPutDto) {
		if(cursoPutDto.categoria() != null) {
			this.categoria = cursoPutDto.categoria();
		}
		if(cursoPutDto.nombre() != null) {
			this.nombre = cursoPutDto.nombre();
		}

	}
}
