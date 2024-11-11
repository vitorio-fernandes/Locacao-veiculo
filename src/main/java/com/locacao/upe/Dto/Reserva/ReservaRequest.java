package com.locacao.upe.Dto.Reserva;

import java.time.LocalDate;
import java.util.UUID;



public record ReservaRequest(

UUID veiculoId,
UUID usuarioId,
LocalDate dataInicio,
LocalDate dataFim
){} 