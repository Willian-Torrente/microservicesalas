package com.reservasala.reserva_sala.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReservaDTO {
    private Long id;
    private LocalDateTime dataHora;
    private Long salaId;
    private Long usuarioId;
}