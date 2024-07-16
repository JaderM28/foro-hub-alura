package com.valhalla.foro_hub_alura.domain.topicos;

public record ResponsePostDto(
        Long id,
        String titulo,
        String mensaje,
        String fechaDeCreacion
) {

    public ResponsePostDto(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion()
        );
    }
}
