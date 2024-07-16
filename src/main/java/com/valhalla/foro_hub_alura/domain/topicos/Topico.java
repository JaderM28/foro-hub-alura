package com.valhalla.foro_hub_alura.domain.topicos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String fechaDeCreacion;

    @Column(nullable = false)
    private Boolean status = true;

    private String autor;

    private String curso;

    public Topico(RequestPostDto requestPostDto){
        if(requestPostDto.idUsuario() != null){
            this.id = Long.valueOf(requestPostDto.idUsuario());
        }
        this.titulo = requestPostDto.titulo();
        this.fechaDeCreacion = LocalDateTime.now().toString();
        this.mensaje = requestPostDto.mensaje();
        this.curso = requestPostDto.nombreDelCurso();
    }

}
