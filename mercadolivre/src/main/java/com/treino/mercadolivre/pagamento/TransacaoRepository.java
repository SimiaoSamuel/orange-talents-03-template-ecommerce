package com.treino.mercadolivre.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Integer> {
}
