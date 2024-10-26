package com.locacao.upe.Dto.Veiculo;

import java.util.UUID;

import com.locacao.upe.Enum.StatusVeiculo;

public record VeiculoResponse(
    UUID id,
    String marca,
    String modelo,
    String ano,
    String categoria,
    double tarifaDia,
    StatusVeiculo statusVeiculo) {
}
