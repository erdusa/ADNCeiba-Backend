package com.ceiba.cliente.puerto.dao;

import com.ceiba.cliente.modelo.dto.DtoCliente;

public interface DaoCliente {

    /**
     * Permite consultar los clientes por número de documento
     *
     * @param documento documento a consultar
     * @return el cliente consultado
     */
    DtoCliente consultarPorDocumento(String documento);
}
