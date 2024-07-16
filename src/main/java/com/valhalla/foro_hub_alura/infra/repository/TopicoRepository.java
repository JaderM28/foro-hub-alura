package com.valhalla.foro_hub_alura.infra.repository;

import com.valhalla.foro_hub_alura.domain.topicos.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
