package com.locacao.upe.Dto.Reserva;

import java.time.LocalDate;
import java.util.UUID;


import com.locacao.upe.Dto.Usuario.UsuarioResponse;
import com.locacao.upe.Dto.Veiculo.VeiculoResponse;

public record ReservaResponse(
  UUID idReserva,
  UsuarioResponse usuarioResponse,
  VeiculoResponse veiculoResponse,
  LocalDate dataInicio,
  LocalDate dataFim,
  double valorTotal
) {}
