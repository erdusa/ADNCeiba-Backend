package com.ceiba.cliente.consulta;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarCliente {

    private final DaoCliente daoCliente;

    public ManejadorConsultarCliente(DaoCliente daoCliente) {
        this.daoCliente = daoCliente;
    }

    public DtoCliente ejecutar(String documento) {
        return this.daoCliente.consultarPorDocumento(documento);
    }
}
