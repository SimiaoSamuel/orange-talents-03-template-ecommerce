package com.treino.mercadolivre.compra;

import com.treino.mercadolivre.pagamento.CompraStatus;
import com.treino.mercadolivre.pagamento.PagamentoGateway;
import com.treino.mercadolivre.pagamento.Transacao;
import com.treino.mercadolivre.pagamento.TransacaoRepository;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantidade;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario comprador;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private PagamentoGateway pagamento;

    @Enumerated(EnumType.STRING)
    private CompraStatus status;

    @OneToMany(mappedBy = "compra")
    private Set<Transacao> transacaos = new HashSet<>();

    public boolean adicionaTransacaoFinalizada(Transacao transacao, TransacaoRepository repository){
        long tamanhoTransacaos = transacaos.stream().filter(t -> t.getStatus() == CompraStatus.SUCESSO).count();

        if(tamanhoTransacaos >= 1) {
            return false;
        }
        if(transacao.getStatus().ordinal() == 0) {
            repository.save(transacao);
            return false;
        }

        repository.save(transacao);
        return true;
    }

    public Integer getId() {
        return id;
    }

    public CompraStatus getStatus() {
        return status;
    }

    public PagamentoGateway getPagamento() {
        return pagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Compra(Integer quantidade, PagamentoGateway pagamento, Produto produto, Usuario comprador, CompraStatus status) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.comprador = comprador;
        this.valor = produto.getValor().multiply(new BigDecimal(quantidade));
        this.pagamento = pagamento;
        this.status = status;
    }

    @Deprecated
    public Compra() {
    }
}
