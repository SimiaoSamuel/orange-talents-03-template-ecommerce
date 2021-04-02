package com.treino.mercadolivre.ranking;

import com.treino.mercadolivre.usuario.Usuario;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Ranking {
    private Set<Rank> ranks = new HashSet<>();

    public void adicionaNovaCompra(Rank rankeado, Integer compraId){
        if(ranks.isEmpty()) {
            ranks.add(rankeado);
            return;
        }
        ranks.forEach(r -> {
            if(r.getUsuarioId().equals(rankeado.getUsuarioId()))
                r.adicionaNovaVenda(compraId);
            else
                ranks.add(rankeado);
        });
    }

    public List<Rank> geraRank(){
        return ranks.stream().sorted(Comparator.comparing(Rank::getVendas)).collect(Collectors.toList());
    }
}
