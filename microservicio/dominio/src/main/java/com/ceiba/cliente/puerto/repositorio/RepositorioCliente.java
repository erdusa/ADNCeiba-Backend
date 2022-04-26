package com.ceiba.cliente.puerto.repositorio;

import com.ceiba.cliente.modelo.entidad.Cliente;

public interface RepositorioCliente {

    /**
     * Permite consultar los datos del cliente por el identificador
     *
     * @param idCliente identificador del cliente
     * @return datos del cliente
     */
    Cliente consultar(Long idCliente);
}
