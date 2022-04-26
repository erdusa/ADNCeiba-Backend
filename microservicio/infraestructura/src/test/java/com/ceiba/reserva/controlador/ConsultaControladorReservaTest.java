package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebMvcTest(ConsultaControladorReserva.class)
class ConsultaControladorReservaTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deberiaListarLasReservasVigentesPorCliente() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/reservas/cliente/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].fechaInicial", is("3022-04-20 07:00:00")))
                .andExpect(jsonPath("$[0].fechaFinal", is("3022-04-21 07:00:00")))
                .andExpect(jsonPath("$[0].marca", is("FORD")))
                .andExpect(jsonPath("$[0].modelo", is(2022)))
                .andExpect(jsonPath("$[0].placa", is("234")))
                .andExpect(jsonPath("$[0].gama", is("MEDIA")))
                .andExpect(jsonPath("$[0].valor", is(1000.0)))
        ;
    }

    @Test
    void deberiaListarLosCarrosGamaAltaDisponibles() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/reservas/carros-disponibles?fechaInicial=20/04/2022&fechaFinal=22/04/2022&gama=ALTA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].idCarro", is(4)))
                .andExpect(jsonPath("$[0].marca", is("MERCEDES")))
                .andExpect(jsonPath("$[0].modelo", is(2021)))
                .andExpect(jsonPath("$[0].placa", is("345")))
                .andExpect(jsonPath("$[0].valor", is(200D)))
                .andExpect(jsonPath("$[1].idCarro", is(5)))
                .andExpect(jsonPath("$[1].marca", is("FERRARI")))
                .andExpect(jsonPath("$[1].modelo", is(2022)))
                .andExpect(jsonPath("$[1].placa", is("346")))
                .andExpect(jsonPath("$[1].valor", is(200D)))
                .andExpect(jsonPath("$[2].idCarro", is(6)))
                .andExpect(jsonPath("$[2].marca", is("BENTLEY")))
                .andExpect(jsonPath("$[2].modelo", is(2020)))
                .andExpect(jsonPath("$[2].placa", is("347")))
                .andExpect(jsonPath("$[2].valor", is(200D)))
        ;

    }

    @Test
    void deberiaListarLosCarrosGamaMediaDisponibles() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/reservas/carros-disponibles?fechaInicial=19/04/2022&fechaFinal=20/04/2022&gama=MEDIA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].idCarro", is(3)))
                .andExpect(jsonPath("$[1].marca", is("CHEVROLET")))
                .andExpect(jsonPath("$[1].modelo", is(2022)))
                .andExpect(jsonPath("$[1].placa", is("235")))
                .andExpect(jsonPath("$[1].valor", is(70D)))
        ;

    }

    @Test
    void deberiaListarLosCarrosGamaBajaDisponibles() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/reservas/carros-disponibles?fechaInicial=19/04/2022&fechaFinal=20/04/2023&gama=BAJA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idCarro", is(1)))
                .andExpect(jsonPath("$[0].marca", is("RENAULT")))
                .andExpect(jsonPath("$[0].modelo", is(2020)))
                .andExpect(jsonPath("$[0].placa", is("123")))
                .andExpect(jsonPath("$[0].valor", is(18_820D)))
        ;

    }

}