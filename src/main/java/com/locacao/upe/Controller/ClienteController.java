package com.locacao.upe.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locacao.upe.Dto.Reserva.ReservaRequest;
import com.locacao.upe.Dto.Reserva.ReservaResponse;
import com.locacao.upe.Dto.Veiculo.VeiculoResponse;
import com.locacao.upe.Models.Reserva;
import com.locacao.upe.Models.Veiculo;
import com.locacao.upe.Service.ReservaService;
import com.locacao.upe.Service.VeiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

  @Autowired
  VeiculoService veiculoService;
  @Autowired
  ReservaService reservaService;

  @PostMapping("/reserva")
  public ResponseEntity<ReservaResponse> criarReserva(@Valid @RequestBody ReservaRequest request) {
    Reserva reserva = reservaService.criarReserva(request);
    ReservaResponse response = new ReservaResponse(
        reserva.getId(),
        reserva.getVeiculo(),
        reserva.getDataInicio(),
        reserva.getDataFim(),
        reserva.getValorTotal());

    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<List<VeiculoResponse>> listarVeiculos(){
    List<VeiculoResponse> response = new ArrayList<>();

    for (Veiculo veiculo : veiculoService.listarVeiculos()) {
      VeiculoResponse veiculoResponse = new VeiculoResponse(
        veiculo.getMarca(), 
        veiculo.getModelo(), 
        veiculo.getAno(), 
        veiculo.getCategoria(), 
        veiculo.getTarifaDia(), 
        veiculo.getStatusVeiculo());
        response.add(veiculoResponse);
    }
    return ResponseEntity.ok(response);
  }
}
