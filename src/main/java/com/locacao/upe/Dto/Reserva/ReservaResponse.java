package com.locacao.upe.Dto.Reserva;

import java.time.LocalDate;
import java.util.UUID;

import com.locacao.upe.Models.Veiculo;

public record ReservaResponse(
  UUID id,
  Veiculo veiculo,
  LocalDate dataInicio,
  LocalDate dataFim,
  double valorTotal

) {}
