package com.locacao.upe.Dto.Veiculo;

import com.locacao.upe.Enum.StatusVeiculo;

public record VeiculoResponse(
    String marca,
    String modelo,
    String ano,
    String categoria,
    double tarifaDia,
    StatusVeiculo statusVeiculo) {}
