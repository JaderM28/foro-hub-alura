package com.valhalla.foro_hub_alura.controller;

import com.valhalla.foro_hub_alura.domain.topicos.RequestPostDto;
import com.valhalla.foro_hub_alura.domain.topicos.RequestPutDto;
import com.valhalla.foro_hub_alura.domain.topicos.ResponsePostDto;
import com.valhalla.foro_hub_alura.domain.topicos.Topico;
import com.valhalla.foro_hub_alura.infra.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<ResponsePostDto> createTopicos(
            @Valid @RequestBody RequestPostDto requestPostDto,
            UriComponentsBuilder uriComponentsBuilder){

        Topico topicoCreado = topicoRepository.save(new Topico(requestPostDto));

        URI url = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topicoCreado.getId()).toUri();

        ResponsePostDto response = new ResponsePostDto(
                topicoCreado.getId(),
                topicoCreado.getTitulo(),
                topicoCreado.getMensaje(),
                topicoCreado.getFechaDeCreacion()
                );

        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public Page<ResponsePostDto> findAllTopicos(@PageableDefault(size = 4) Pageable pageable){
        var datos = topicoRepository.findAll(pageable)
                .map(ResponsePostDto::new);

        return datos;
    }

    @GetMapping("/{id}")
    public ResponseEntity findOneTopicos(
            @PathVariable Long id){

        Optional<Topico> datosTopico = topicoRepository.findById(id);
        if(datosTopico.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Topico topicoEncontrado = datosTopico.get();

        var response = new ResponsePostDto(topicoEncontrado);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTopicos(
            @PathVariable Long id,
            @RequestBody RequestPutDto requestPutDto){

        Optional<Topico> datosTopico = topicoRepository.findById(id);
        if(datosTopico.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Topico topicoEncontrado = datosTopico.get();

        if(requestPutDto.idUsuario() != null){
            topicoEncontrado.setId(Long.valueOf(requestPutDto.idUsuario()));
        }

        if(requestPutDto.titulo() != null){
            topicoEncontrado.setTitulo(requestPutDto.titulo());
        }

        if(requestPutDto.mensaje() != null){
            topicoEncontrado.setMensaje(requestPutDto.mensaje());
        }

        if(requestPutDto.nombreDelCurso() != null){
            topicoEncontrado.setCurso(requestPutDto.nombreDelCurso());
        }

        Topico topicoGuardado = topicoRepository.save(topicoEncontrado);
        var response = new ResponsePostDto(topicoGuardado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopicos( @PathVariable Long id){
        Optional<Topico> datosTopico = topicoRepository.findById(id);
        if(datosTopico.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(datosTopico.get().getId());
        return ResponseEntity.noContent().build();
    }

}
