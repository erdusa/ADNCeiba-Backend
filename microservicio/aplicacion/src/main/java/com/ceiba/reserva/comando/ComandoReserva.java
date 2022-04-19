package com.ceiba.reserva.comando;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class ComandoReserva {

    private Long id;
    private Long idCliente;
    private Long idCarro;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaFinal;

}
