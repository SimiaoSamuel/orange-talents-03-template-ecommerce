package com.treino.mercadolivre.pergunta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PerguntaRepository extends JpaRepository<Pergunta,Integer> {
    List<Pergunta> findByProdutoId(Integer id);
}
