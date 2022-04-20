package com.ceiba.comun;

public final class NumberUtils {

    private NumberUtils() {
    }

    public static double sumarPorcentaje(double valor, double porcentaje) {
        return valor + valor * porcentaje / 100;
    }

}
