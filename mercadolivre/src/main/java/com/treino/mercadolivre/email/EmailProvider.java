package com.treino.mercadolivre.email;

import com.treino.mercadolivre.compra.Compra;
import com.treino.mercadolivre.pergunta.Pergunta;
import com.treino.mercadolivre.produto.Produto;
import com.treino.mercadolivre.usuario.Usuario;

public interface EmailProvider {
    void sendEmail(Produto produto, Pergunta pergunta);
    void sendEmail(Compra compra);
    void sendEmailFinishBuy(Compra compra);
    void sendEmailWhenBuyFail(Compra compra);
}
