package com.valhalla.foro_hub_alura.infra.auth;

public record DatosJwtTokenDto(
    String token,
    String type
) {
}
