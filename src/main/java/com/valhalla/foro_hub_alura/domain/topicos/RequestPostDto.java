package com.valhalla.foro_hub_alura.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record RequestPostDto(

        String idUsuario,

        @NotBlank
        String mensaje,

        @NotBlank
        String nombreDelCurso,

        @NotBlank
        String titulo
) {
}
