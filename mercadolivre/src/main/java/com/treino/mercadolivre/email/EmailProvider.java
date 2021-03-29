package com.treino.mercadolivre.email;

import com.treino.mercadolivre.pergunta.Pergunta;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;

public interface EmailProvider {
    void sendEmailTo(Produto produto, Pergunta pergunta);
}
