package com.ceiba.reserva.controlador;

import com.ceiba.carro.enums.EnumGama;
import com.ceiba.reserva.consulta.ManejadorListarCarrosDisponibles;
import com.ceiba.reserva.consulta.ManejadorListarPorCliente;
import com.ceiba.reserva.modelo.dto.DtoCarroDisponible;
import com.ceiba.reserva.modelo.dto.DtoReservaVigente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@Api(tags = {"Controlador para consultar reservas"})
public class ConsultaControladorReserva {

    private final ManejadorListarPorCliente manejadorListarPorCliente;
    private final ManejadorListarCarrosDisponibles manejadorListarCarrosDisponibles;

    public ConsultaControladorReserva(ManejadorListarPorCliente manejadorListarPorCliente, ManejadorListarCarrosDisponibles manejadorListarCarrosDisponibles) {
        this.manejadorListarPorCliente = manejadorListarPorCliente;
        this.manejadorListarCarrosDisponibles = manejadorListarCarrosDisponibles;
    }

    @GetMapping("/cliente/{idCliente}")
    @ApiOperation("Listar las reservas vigentes para un cliente")
    public List<DtoReservaVigente> listarPorCliente(@PathVariable Long idCliente) {
        return this.manejadorListarPorCliente.ejecutar(idCliente);
    }

    @GetMapping("/carros-disponibles")
    public List<DtoCarroDisponible> listarCarrosDisponibles(@RequestParam String fechaInicial, @RequestParam String fechaFinal, @RequestParam EnumGama gama) {
        LocalDate fFechaInicial = LocalDate.parse(fechaInicial, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate fFechaFinal = LocalDate.parse(fechaFinal, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return this.manejadorListarCarrosDisponibles.ejecutar(fFechaInicial, fFechaFinal, gama);
    }
}
