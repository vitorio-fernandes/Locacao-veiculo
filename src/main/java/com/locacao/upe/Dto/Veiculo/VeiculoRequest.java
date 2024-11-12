package com.locacao.upe.Dto.Veiculo;

import com.locacao.upe.Enum.StatusVeiculo;

public record VeiculoRequest(

    String marca,
    String modelo,
    String ano,
    String categoria,
    double tarifaDia,
    StatusVeiculo statusVeiculo

) {}
