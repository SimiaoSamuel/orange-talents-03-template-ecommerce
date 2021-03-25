package com.treino.mercadolivre.token;

public class TokenResponse {
    private String token;
    private String tipo;

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public TokenResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "token='" + token + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
