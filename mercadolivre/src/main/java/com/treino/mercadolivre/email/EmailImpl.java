package com.treino.mercadolivre.email;

import com.treino.mercadolivre.compra.Compra;
import com.treino.mercadolivre.pergunta.Pergunta;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EmailImpl implements EmailProvider{

    @Override
    public void sendEmail(Produto produto, Pergunta pergunta) {
        System.out.println("Pergunta: " + pergunta.getTitulo() +" from "+ pergunta.getUsuario() +
                " send to " + produto.getUsuario());
    }

    @Override
    public void sendEmail(Compra compra) {
        System.out.println(compra.getComprador().getLogin() + " quer comprar o seu produto: "
                + compra.getProduto().getNome() + ", senhor " + compra.getProduto().getUsuario().getLogin());
    }
}
