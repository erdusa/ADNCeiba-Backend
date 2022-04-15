package com.ceiba.cliente.puerto.dao;

import com.ceiba.cliente.modelo.entidad.Cliente;

public interface DaoCliente {

    /**
     * Permite consultar los clientes por número de documento
     *
     * @param documento documento a consultar
     * @return el cliente consultado
     */
    Cliente consultarPorDocumento(String documento);
}
