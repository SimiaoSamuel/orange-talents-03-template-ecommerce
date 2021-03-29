package com.treino.mercadolivre.opiniao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpiniaoRepository extends JpaRepository<Opiniao,Integer> {
    List<Opiniao> findByProdutoId(Integer id);

}
