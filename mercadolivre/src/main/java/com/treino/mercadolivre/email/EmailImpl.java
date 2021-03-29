package com.treino.mercadolivre.email;

import com.treino.mercadolivre.pergunta.Pergunta;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EmailImpl implements EmailProvider{

    @Override
    public void sendEmailTo(Produto produto, Pergunta pergunta) {
        System.out.println("Pergunta: " + pergunta.getTitulo() +" from "+ produto.getUsuario() +
                " send to " + produto.getUsuario());
    }
}
