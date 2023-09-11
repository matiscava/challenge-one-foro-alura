package com.alura.foro.service;

import com.alura.foro.dto.usuario.UsuarioPostDto;
import com.alura.foro.dto.usuario.UsuarioPutDto;
import com.alura.foro.dto.usuario.UsuarioRespuestaDto;
import com.alura.foro.errores.ValidacionRechazada;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.IUsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    IUsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioRespuestaDto registrar(UsuarioPostDto usuarioPostDto) {
        String contrasena = passwordEncoder.encode(usuarioPostDto.contrasena());
        Usuario usuario = new Usuario(usuarioPostDto, contrasena);
        usuarioRepository.save(usuario);

        return new UsuarioRespuestaDto(usuario);
    }

    public List<UsuarioRespuestaDto> getAll() {
        List<Usuario> usuarioList = usuarioRepository.findAll();
        List<UsuarioRespuestaDto> usuarioRespuestaDtoList = new ArrayList<>();
        for (Usuario u :
                usuarioList) {
            usuarioRespuestaDtoList.add(new UsuarioRespuestaDto(u));
        }

        return usuarioRespuestaDtoList;
    }

    public UsuarioRespuestaDto getById(Long id) {
        if(!usuarioRepository.existsById(id)){
            throw new ValidacionRechazada("No existe el usuario con ese Id");
        }
        Usuario usuario = usuarioRepository.getReferenceById(id);

        return new UsuarioRespuestaDto(usuario);
    }

    public void eliminar(Long id) {
        if(!usuarioRepository.existsById(id)){
            throw new ValidacionRechazada("No existe el usuario con ese Id");
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioRespuestaDto editar(Long id, UsuarioPutDto usuarioPutDto) {
        if(!usuarioRepository.existsById(id)){
            throw new ValidacionRechazada("No existe el usuario con ese Id");
        }
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.actualizarDatos(usuarioPutDto);
        usuarioRepository.save(usuario);
        return new UsuarioRespuestaDto(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }
}
