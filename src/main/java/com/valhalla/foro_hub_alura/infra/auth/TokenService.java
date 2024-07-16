package com.valhalla.foro_hub_alura.infra.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.valhalla.foro_hub_alura.domain.topicos.Topico;
import com.valhalla.foro_hub_alura.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken( Usuario usuario){

        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String tokenAuth = JWT.create()
                    .withIssuer("valhalla")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withClaim("correo", usuario.getCorreoElectronico())
                    .withExpiresAt(FechaExpiracion())
                    .sign(algorithm);
            return tokenAuth;
        }catch(JWTCreationException e){
            throw new RuntimeException(e);
        }

    }

    public String getSubject(String tokenAuth){

        DecodedJWT verifier = null;

        try{

            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("valhalla")
                    .build()
                    .verify(tokenAuth);
            verifier.getSubject();


        }catch (JWTVerificationException e){
            throw new RuntimeException(e);
        }

        if(verifier.getSubject() == null){
            throw new RuntimeException("Error, la comprobacion del token no valida!");
        }

        return verifier.getSubject();
    }

    private Instant FechaExpiracion(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
    }

}
