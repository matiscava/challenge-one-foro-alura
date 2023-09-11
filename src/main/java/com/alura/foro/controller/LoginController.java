package com.alura.foro.controller;

import com.alura.foro.dto.JWTtoken.JWTTonkenDto;
import com.alura.foro.dto.usuario.UsuarioLoginDto;
//import com.alura.foro.infra.security.TokenService;
//import com.alura.foro.modelo.Usuario;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    private ResponseEntity<JWTTonkenDto> autenticarUsuario(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto){
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuarioLoginDto.email(),usuarioLoginDto.contrasena());
        authenticationManager.authenticate(authToken);
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        String JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTTonkenDto(JWTToken));
    }
}
