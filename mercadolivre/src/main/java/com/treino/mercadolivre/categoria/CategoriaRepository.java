package com.treino.mercadolivre.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    Optional<Categoria> findByNome(String nome);
}
