package com.valhalla.foro_hub_alura.controller;

import com.valhalla.foro_hub_alura.domain.usuarios.DatosUsuarioDto;
import com.valhalla.foro_hub_alura.domain.usuarios.Usuario;
import com.valhalla.foro_hub_alura.infra.auth.DatosJwtTokenDto;
import com.valhalla.foro_hub_alura.infra.auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarLogin(@RequestBody @Valid DatosUsuarioDto datosUsuarioDto){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosUsuarioDto.username(),
                datosUsuarioDto.password());

        var userAuth = authenticationManager.authenticate(authToken);
        var token = tokenService.generarToken((Usuario) userAuth.getPrincipal());
        return ResponseEntity.ok(new DatosJwtTokenDto(token, "Bearer"));
    }

}
