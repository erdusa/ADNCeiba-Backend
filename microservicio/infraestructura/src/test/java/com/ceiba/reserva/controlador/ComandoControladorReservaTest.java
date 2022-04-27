package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ComandoReservaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebMvcTest(ComandoControladorReserva.class)
class ComandoControladorReservaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void deberiaCrearReserva() throws Exception {
        // arrange
        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder().build();
        // act -assert
        mocMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoReserva)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor':4}"));
    }

    @Test
    void deberiaGenerarErrorParaCarroReservado() throws Exception {
        // arrange
        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder()
                .conIdCarro(2L)
                .conFechaInicial(LocalDateTime.of(3022, 4, 20, 7, 0))
                .conDias(1)
                .build();
        // act -assert
        mocMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoReserva)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{'mensaje':'El carro ya está reservado para las fechas seleccionadas'}"));
    }

    @Test
    void deberiaCancelarReserva() throws Exception {
        // arrange
        //usamos el primero que tiene fecha para el 3022
        Long id = 1L;
        // act -assert
        mocMvc.perform(put("/reservas/cancelar/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}