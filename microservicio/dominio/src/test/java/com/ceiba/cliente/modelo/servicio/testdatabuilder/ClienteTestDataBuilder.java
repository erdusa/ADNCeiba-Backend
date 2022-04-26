package com.ceiba.cliente.modelo.servicio.testdatabuilder;

import com.ceiba.cliente.modelo.entidad.Cliente;

public class ClienteTestDataBuilder {

    private Long id;
    private String documento;
    private String nombre;

    public ClienteTestDataBuilder() {
        this.id = 1L;
        this.documento = "1000";
        this.nombre = "TEST";
    }

    public Cliente build() {
        return new Cliente(
                this.id,
                this.documento,
                this.nombre
        );
    }
}
