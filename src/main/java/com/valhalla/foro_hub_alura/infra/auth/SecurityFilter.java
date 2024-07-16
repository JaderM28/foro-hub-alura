package com.valhalla.foro_hub_alura.infra.auth;

import com.valhalla.foro_hub_alura.infra.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenEncabezado = request.getHeader("Authorization");

        if(tokenEncabezado != null && !tokenEncabezado.isEmpty()){

            String token = tokenEncabezado.replace("Bearer", "").trim();
            String subject = tokenService.getSubject(token);

            if(subject != null){
                var usuarioEncontrado = usuarioRepository.findByUsername(subject);

                var authUsuario = new UsernamePasswordAuthenticationToken(usuarioEncontrado, null,
                        usuarioEncontrado.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authUsuario);
            }
        }

        // pasamos al otro filtro
        filterChain.doFilter(request, response);

    }
}
