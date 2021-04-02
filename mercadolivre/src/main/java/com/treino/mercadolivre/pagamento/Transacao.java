package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pagamentoId;

    @ManyToOne
    private Compra compra;

    private CompraStatus status;

    private LocalDateTime instanteCompra = LocalDateTime.now().withNano(0);

    public Integer getId() {
        return id;
    }

    public Integer getPagamentoId() {
        return pagamentoId;
    }

    public Compra getCompra() {
        return compra;
    }

    public CompraStatus getStatus() {
        return status;
    }

    public LocalDateTime getInstanteCompra() {
        return instanteCompra;
    }

    public Transacao(Compra compra, CompraStatus status, Integer pagamentoId) {
        this.compra = compra;
        this.status = status;
        this.pagamentoId = pagamentoId;
    }

    @Deprecated
    public Transacao() {
    }

}
