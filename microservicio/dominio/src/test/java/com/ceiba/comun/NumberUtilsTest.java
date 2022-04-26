package com.ceiba.comun;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberUtilsTest {

    @Test
    void deberiaSerIgualSumandoCeroPorciento() {
        // arrange - act
        double valorRetornado = NumberUtils.sumarPorcentaje(100.0, 0);
        // assert
        assertEquals(100.0, valorRetornado);

    }

    @Test
    void deberiaSerSumarBienDiezPorCiento() {
        // arrange - act
        double valorRetornado1 = NumberUtils.sumarPorcentaje(100.0, 10);
        double valorRetornado2 = NumberUtils.sumarPorcentaje(150.20, 10);
        double valorRetornado3 = NumberUtils.sumarPorcentaje(200.25, 10);
        double valorRetornado4 = NumberUtils.sumarPorcentaje(12, 10);
        // assert
        assertEquals(110.0, valorRetornado1);
        assertEquals(165.22, valorRetornado2);
        assertEquals(220.275, valorRetornado3);
        assertEquals(13.2, valorRetornado4);

    }

    @Test
    void deberiaSerSumarBienCientoPorCiento() {
        // arrange - act
        double valorRetornado1 = NumberUtils.sumarPorcentaje(15.2, 100);
        double valorRetornado2 = NumberUtils.sumarPorcentaje(0, 100);
        double valorRetornado3 = NumberUtils.sumarPorcentaje(30_000.41, 100);
        double valorRetornado4 = NumberUtils.sumarPorcentaje(1_250, 100);
        // assert
        assertEquals(30.4, valorRetornado1);
        assertEquals(0, valorRetornado2);
        assertEquals(60_000.82, valorRetornado3);
        assertEquals(2_500, valorRetornado4);

    }
}