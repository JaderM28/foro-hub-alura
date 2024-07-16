package com.valhalla.foro_hub_alura.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestPutDto(

        String idUsuario,

        String mensaje,

        String nombreDelCurso,

        String titulo
) {
}
