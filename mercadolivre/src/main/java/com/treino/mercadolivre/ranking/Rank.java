package com.treino.mercadolivre.ranking;

import com.treino.mercadolivre.usuario.Usuario;

import java.util.HashSet;
import java.util.Set;

public class Rank {
    private Integer usuarioId;

    private Integer numeroDeVendas = 0;

    Set<Integer> vendas = new HashSet<>();

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void adicionaNovaVenda(Integer compra) {
        if(!vendas.contains(compra))
            this.vendas.add(compra);
    }

    public Integer getVendas() {
        return vendas.size();
    }

    public Rank(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Deprecated
    public Rank() {
    }
}
