package com.locacao.upe.Dto.Reserva;

import java.time.LocalDate;

import com.locacao.upe.Enum.StatusReserva;
import com.locacao.upe.Models.Usuario;
import com.locacao.upe.Models.Veiculo;

public record ReservaRequest(

Veiculo veiculo,
Usuario usuario,
LocalDate dataInicio,
LocalDate dataFim,
double valorTotal,
StatusReserva statusReserva

){} 