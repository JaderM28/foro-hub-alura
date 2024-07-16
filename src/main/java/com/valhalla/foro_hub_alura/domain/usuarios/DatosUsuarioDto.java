package com.valhalla.foro_hub_alura.domain.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DatosUsuarioDto(

        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
