package com.alura.foro.modelo;

import com.alura.foro.dto.respuesta.RespuestaEditDto;
import com.alura.foro.dto.respuesta.RespuestaPostDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topico_id")
	private Topico topico;

	private LocalDateTime fechaCreacion = LocalDateTime.now();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	private Boolean solucion = false;

	public Respuesta(RespuestaPostDto respuestaPostDto, Topico topico, Usuario usuario) {
		this.mensaje = respuestaPostDto.mensaje();
		this.topico = topico;
		this.autor = usuario;
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
		Respuesta other = (Respuesta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void actualizarDatos(RespuestaEditDto respuestaEditDto) {
		if(respuestaEditDto.solucion() != this.solucion){
			this.solucion = respuestaEditDto.solucion();
		}
		if (respuestaEditDto.mensaje() != null){
			this.mensaje = respuestaEditDto.mensaje();
		}
	}
}
