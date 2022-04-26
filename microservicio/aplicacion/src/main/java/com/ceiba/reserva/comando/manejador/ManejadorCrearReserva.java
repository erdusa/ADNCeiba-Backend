package com.ceiba.reserva.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.fabrica.FabricaSolicitudReserva;
import com.ceiba.reserva.modelo.entidad.SolicitudReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearReserva implements ManejadorComandoRespuesta<ComandoReserva, ComandoRespuesta<Long>> {

    private final FabricaSolicitudReserva fabricaSolicitudReserva;
    private final ServicioCrearReserva servicioCrearReserva;

    public ManejadorCrearReserva(FabricaSolicitudReserva fabricaSolicitudReserva, ServicioCrearReserva servicioCrearReserva) {
        this.fabricaSolicitudReserva = fabricaSolicitudReserva;
        this.servicioCrearReserva = servicioCrearReserva;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoReserva comandoReserva) {
        SolicitudReserva solicitudReserva = this.fabricaSolicitudReserva.crear(comandoReserva);
        return new ComandoRespuesta<>(this.servicioCrearReserva.ejecutar(solicitudReserva));
    }
}
