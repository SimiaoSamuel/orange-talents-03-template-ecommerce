package com.treino.mercadolivre.pagamento;

import com.treino.mercadolivre.compra.Compra;
import com.treino.mercadolivre.compra.CompraRepository;
import com.treino.mercadolivre.email.EmailProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/retorno-{gateway}/{id}")
public class PagamentoController {

    private final CompraRepository compraRepository;
    private final TransacaoRepository transacaoRepository;
    private final EmailProvider email;

    public PagamentoController(CompraRepository compraRepository, TransacaoRepository transacaoRepository, EmailProvider email) {
        this.compraRepository = compraRepository;
        this.transacaoRepository = transacaoRepository;
        this.email = email;
    }

    @PostMapping
    public ResponseEntity<Transacao> processaPagamento(@RequestBody DefaultGateway gateway,
                                                    @PathVariable(name = "gateway") String value,
                                                    @PathVariable(name = "id") Integer id) throws BindException {
        Optional<Compra> compraOptional = compraRepository.findById(id);

        if (compraOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Compra compra = compraOptional.get();
        boolean sameGateway = compra.getPagamento().getName().equalsIgnoreCase(value);

        if(!sameGateway)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        IPagamento iPagamento = gateway.toNewGateway(value);
        Transacao transacao = iPagamento.toTransacao(compra);

        boolean isCompraSucedida = compra.adicionaTransacaoFinalizada(transacao,transacaoRepository);

        if(!isCompraSucedida){
            BindException erroCompra = new BindException(gateway,
                    "defaultGateway");
            erroCompra.reject(null,
                    "Seu pagamento falhou!");

            email.sendEmailWhenBuyFail(compra);
            throw erroCompra;
        }

        email.sendEmailFinishBuy(compra);
        return ResponseEntity.ok(transacao);
    }
}
