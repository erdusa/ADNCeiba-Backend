package com.ceiba.cliente.controlador;

import com.ceiba.cliente.consulta.ManejadorConsultarCliente;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@Api(tags = {"Controlador consulta clientes"})
public class ConsultaControladorCliente {

    private final ManejadorConsultarCliente manejadorConsultarCliente;

    public ConsultaControladorCliente(ManejadorConsultarCliente manejadorConsultarCliente) {
        this.manejadorConsultarCliente = manejadorConsultarCliente;
    }

    @GetMapping("/{documento}")
    @ApiOperation("Consultar cliente por numero de documento de identidad")
    public DtoCliente consultarPorDocumento(@PathVariable String documento) {
        return this.manejadorConsultarCliente.ejecutar(documento);
    }
}
