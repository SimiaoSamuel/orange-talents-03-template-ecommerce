package com.treino.mercadolivre.notafiscal;

import com.treino.mercadolivre.compra.CompraRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/notasfiscais")
public class NotaFiscalController {

    private final CompraRepository compraRepository;

    public NotaFiscalController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @PostMapping
    public ResponseEntity<NotaFiscalResponse> geraNotaFiscal(@RequestBody @Valid NotaFiscalRequest notaFiscalRequest){
        NotaFiscalResponse notaFiscalResponse = notaFiscalRequest.geraNota(compraRepository);

        return ResponseEntity.ok(notaFiscalResponse);
    }
}
