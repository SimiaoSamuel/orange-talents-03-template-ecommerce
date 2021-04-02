package com.treino.mercadolivre.notafiscal;

import com.treino.mercadolivre.compra.Compra;
import com.treino.mercadolivre.compra.CompraRepository;
import com.treino.mercadolivre.usuario.Usuario;
import com.treino.mercadolivre.validation.annotations.ExistInDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {
    @NotNull @ExistInDatabase(klass = Compra.class, fieldName = "id")
    private Integer compraId;

    @NotNull @ExistInDatabase(klass = Usuario.class, fieldName = "id")
    private Integer usuarioId;

    public NotaFiscalRequest(@NotNull Integer compraId, @NotNull Integer usuarioId) {
        this.compraId = compraId;
        this.usuarioId = usuarioId;
    }

    public NotaFiscalResponse geraNota(CompraRepository repository){
        Compra compra = repository.getOne(compraId);

        Integer compradorId = compra.getComprador().getId();
        boolean usuarioIsBuyer = usuarioId.equals(compradorId);

        if(!usuarioIsBuyer) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Esse usuario não comprou este produto!");
        }

        return new NotaFiscalResponse(compra);
    }
}
