package com.GlobalFuture.MentalHealth.domain.model;

public enum NivelHumor {
    MUITO_RUIM(1),
    RUIM(2),
    NEUTRO(3),
    BOM(4),
    MUITO_BOM(5);

    private final int valor;

    NivelHumor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
