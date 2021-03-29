package com.treino.mercadolivre.produto;

import com.treino.mercadolivre.caracteristica.Caracteristica;
import com.treino.mercadolivre.caracteristica.CaracteristicaResponse;
import com.treino.mercadolivre.categoria.CategoriaResponse;
import com.treino.mercadolivre.foto.Foto;
import com.treino.mercadolivre.foto.FotoResponse;
import com.treino.mercadolivre.opiniao.Opiniao;
import com.treino.mercadolivre.opiniao.OpiniaoResponse;
import com.treino.mercadolivre.pergunta.Pergunta;
import com.treino.mercadolivre.pergunta.PerguntaResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoResponse {
    private Integer id;
    private String vendedor;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private String descricao;
    private List<CaracteristicaResponse> caracteristicas;
    private CategoriaResponse categoria;

    private List<FotoResponse> uris;
    private Double mediaNotas;
    private Integer numeroDeNotas;
    private List<PerguntaResponse> perguntas;
    private List<OpiniaoResponse> opinioes;

    public List<FotoResponse> getUris() {
        return uris;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public String getVendedor() {
        return vendedor;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getNumeroDeNotas() {
        return numeroDeNotas;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public ProdutoResponse(Produto produto, List<Opiniao> opinioes, List<Pergunta> perguntas) {
        this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaResponse::new)
                .collect(Collectors.toList());
        this.opinioes = opinioes.stream().map(OpiniaoResponse::new).collect(Collectors.toList());
        this.perguntas = perguntas.stream().map(PerguntaResponse::new).collect(Collectors.toList());
        this.numeroDeNotas = opinioes.size();
        this.mediaNotas = opinioes.stream().map(Opiniao::getNota).mapToDouble(Byte::doubleValue)
                .average().orElse(0.0);

        this.id = produto.getId();
        this.categoria = new CategoriaResponse(produto.getCategoria());
        this.descricao = produto.getDescricao();
        this.quantidade = produto.getQuantidade();
        this.valor = produto.getValor();
        this.nome = produto.getNome();
        this.vendedor = produto.getUsuario().getLogin();
        this.uris = FotoResponse.toResponse(produto.getFotos());
    }
}
