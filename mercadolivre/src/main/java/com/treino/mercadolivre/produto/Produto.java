package com.treino.mercadolivre.produto;

import com.treino.mercadolivre.caracteristica.Caracteristica;
import com.treino.mercadolivre.categoria.Categoria;
import com.treino.mercadolivre.foto.Foto;
import com.treino.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidade;

    @OneToMany
    private List<Caracteristica> caracteristicas;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private LocalDateTime instanteCadastro = LocalDateTime.now().withNano(0);

    @OneToMany
    private List<Foto> fotos = new ArrayList<>();

    public void setFotos(List<Foto> fotos) {
        this.fotos.addAll(fotos);
    }

    public List<Foto> getFotos() {
        return fotos;
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

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, List<Caracteristica> caracteristicas, String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    @Deprecated
    public Produto() {
    }
}
