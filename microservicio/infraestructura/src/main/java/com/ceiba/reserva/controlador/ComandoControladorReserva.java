package com.ceiba.reserva.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCancelarReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCrearReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@Api(tags = {"Controlador comando reserva"})
public class ComandoControladorReserva {

    private final ManejadorCrearReserva manejadorCrearReserva;
    private final ManejadorCancelarReserva manejadorCancelarReserva;

    public ComandoControladorReserva(ManejadorCrearReserva manejadorCrearReserva, ManejadorCancelarReserva manejadorCancelarReserva) {
        this.manejadorCrearReserva = manejadorCrearReserva;
        this.manejadorCancelarReserva = manejadorCancelarReserva;
    }

    @PostMapping
    @ApiOperation("Crear reserva")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoReserva comandoReserva) {
        return this.manejadorCrearReserva.ejecutar(comandoReserva);
    }

    @PutMapping("/cancelar/{id}")
    @ApiOperation("Cancelar reserva")
    public void cancelar(@PathVariable Long id) {
        this.manejadorCancelarReserva.ejecutar(id);
    }
}
