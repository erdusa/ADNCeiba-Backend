package com.ceiba.configuracion;

import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioCancelarReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva) {
        return new ServicioCrearReserva(repositorioReserva);
    }

    @Bean
    public ServicioCancelarReserva servicioCancelarReserva(RepositorioReserva repositorioReserva) {
        return new ServicioCancelarReserva(repositorioReserva);
    }
}
