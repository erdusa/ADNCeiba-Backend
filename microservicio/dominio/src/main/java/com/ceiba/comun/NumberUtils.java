package com.ceiba.comun;

public class NumberUtils {

    private NumberUtils() {
    }

    public static double sumarPorcentaje(double valor, double porcentaje) {
        return valor + valor * porcentaje / 100;
    }

}
