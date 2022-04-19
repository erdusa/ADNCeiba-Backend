package com.ceiba.carro.modelo.servicio.testdatabuilder;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.carro.modelo.entidad.Carro;

public class CarroTestDataBuilder {

    private Long id;
    private String marca;
    private Integer modelo;
    private String placa;
    private EnumGama gama;

    public CarroTestDataBuilder() {
        this.id = 1L;
        this.marca = "RENAULT";
        this.modelo = 2022;
        this.placa = "123";
        this.gama = EnumGama.MEDIA;
    }

    public CarroTestDataBuilder conGama(EnumGama gama) {
        this.gama = gama;
        return this;
    }

    public Carro build() {
        return new Carro(this.id,
                this.marca,
                this.modelo,
                this.placa,
                this.gama);
    }
}
