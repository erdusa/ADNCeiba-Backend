package com.ceiba.cliente.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String documento;
    private String nombre;
}
