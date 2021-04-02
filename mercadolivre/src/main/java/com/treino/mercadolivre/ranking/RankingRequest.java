package com.treino.mercadolivre.ranking;

import com.treino.mercadolivre.compra.Compra;
import com.treino.mercadolivre.compra.CompraRepository;
import com.treino.mercadolivre.usuario.Usuario;
import com.treino.mercadolivre.usuario.UsuarioRepository;
import com.treino.mercadolivre.validation.annotations.ExistInDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

public class RankingRequest {
    @NotNull @ExistInDatabase(klass = Compra.class, fieldName = "id")
    private Integer compraId;

    public Integer getCompraId() {
        return compraId;
    }

    @NotNull @ExistInDatabase(klass = Usuario.class, fieldName = "id")
    private Integer vendedorId;

    public RankingRequest(@NotNull Integer compraId, @NotNull Integer vendedorId) {
        this.compraId = compraId;
        this.vendedorId = vendedorId;
    }

    public Rank toRank(CompraRepository repository){
        Compra compra = repository.getOne(compraId);
        Usuario vendedor = compra.getProduto().getUsuario();

        if(!vendedor.getId().equals(vendedorId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Esse vendedor não tem esse produto!");
        return new Rank(vendedorId);
    }
}
