package com.ceiba.carro.enums;

public enum EnumGama {

    ALTA(100),
    MEDIA(70),
    BAJA(50);

    public final double valor;

    EnumGama(double valor) {
        this.valor = valor;
    }
}
