package com.locacao.upe.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locacao.upe.Dto.Reserva.ReservaRequest;
import com.locacao.upe.Dto.Reserva.ReservaResponse;
import com.locacao.upe.Dto.Usuario.UsuarioRequest;
import com.locacao.upe.Dto.Usuario.UsuarioResponse;
import com.locacao.upe.Dto.Veiculo.VeiculoRequest;
import com.locacao.upe.Dto.Veiculo.VeiculoResponse;
import com.locacao.upe.Models.Reserva;
import com.locacao.upe.Models.Usuario;
import com.locacao.upe.Models.Veiculo;
import com.locacao.upe.Service.ReservaService;
import com.locacao.upe.Service.UsuarioService;
import com.locacao.upe.Service.VeiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
  @Autowired
  UsuarioService usuarioService;
  @Autowired
  VeiculoService veiculoService;
  @Autowired
  ReservaService reservaService;

  @PutMapping("/usuario") //Atualizar um usuário e seu Papel de Cliente para ADMIN ou vice versa
  public ResponseEntity<UsuarioResponse> atualizarUsuario(@Valid @RequestBody UsuarioRequest request, UUID id){
    Usuario usuario = usuarioService.atualizarUsuario(id, request);
    UsuarioResponse response = new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getPapel());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/veiculo") //Cadastro Veiculo no sistema   OK !
  public ResponseEntity<VeiculoResponse> cadastroVeiculo(@Valid @RequestBody VeiculoRequest request) {
    Veiculo veiculo = veiculoService.adicionarVeiculo(request);
    VeiculoResponse response = new VeiculoResponse(
        veiculo.getId(),
        veiculo.getMarca(),
        veiculo.getModelo(),
        veiculo.getAno(),
        veiculo.getCategoria(),
        veiculo.getTarifaDia(),
        veiculo.getStatusVeiculo());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/veiculo") // Listar Veiculos OK !
  public ResponseEntity<List<VeiculoResponse>> listarVeiculos() {
    List<VeiculoResponse> response = new ArrayList<>();

    for (Veiculo veiculo : veiculoService.listarVeiculos()) {
      VeiculoResponse veiculoResponse = new VeiculoResponse(
          veiculo.getId(),
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

  @PutMapping("/veiculo/{id}") // Atualizar um veiculo do sistema  OK!
  public ResponseEntity<VeiculoResponse> atualizarVeiculo(@PathVariable UUID id,
      @Valid @RequestBody VeiculoRequest request) {
    Veiculo veiculo = veiculoService.atualizarVeiculo(id, request);
    VeiculoResponse response = new VeiculoResponse(
        veiculo.getId(),
        veiculo.getMarca(),
        veiculo.getModelo(),
        veiculo.getAno(),
        veiculo.getCategoria(),
        veiculo.getTarifaDia(),
        veiculo.getStatusVeiculo());
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("veiculo/{id}") //Deletar um Veiculo OK!
  public ResponseEntity<?> deletarVeiculo(@PathVariable UUID id) {
    veiculoService.deletarVeiculo(id);
    return ResponseEntity.ok("Veiculo Deletado com Sucesso!");
  }

  @GetMapping("/reserva") // Listar todas as reservas do sistema 
  public ResponseEntity<List<ReservaResponse>> listarReservas() {
    List<ReservaResponse> responseList = new ArrayList<>();

    for (Reserva reserva : reservaService.listarReservas()) {
      UsuarioResponse usuarioResponse = new UsuarioResponse(
          reserva.getUsuario().getId(),
          reserva.getUsuario().getNome(),
          reserva.getUsuario().getPapel());
      VeiculoResponse veiculoResponse = new VeiculoResponse(
          reserva.getVeiculo().getId(),
          reserva.getVeiculo().getMarca(),
          reserva.getVeiculo().getModelo(),
          reserva.getVeiculo().getAno(),
          reserva.getVeiculo().getCategoria(),
          reserva.getVeiculo().getTarifaDia(),
          reserva.getVeiculo().getStatusVeiculo());
      
          ReservaResponse response = new ReservaResponse(
          reserva.getId(),
          usuarioResponse,
          veiculoResponse,
          reserva.getDataInicio(),
          reserva.getDataFim(),
          reserva.getValorTotal());

      responseList.add(response);
    }

    return ResponseEntity.ok(responseList);
  }

  @PutMapping("/reserva/{id}") //Atualizar uma reserva
  public ResponseEntity<ReservaResponse> atualizarReserva(@PathVariable UUID id,
      @Valid @RequestBody ReservaRequest request) {
    Reserva reserva = reservaService.atualizarReserva(id, request);

   
    UsuarioResponse usuarioResponse = new UsuarioResponse(
        reserva.getUsuario().getId(),
        reserva.getUsuario().getNome(),
        reserva.getUsuario().getPapel());

    
    VeiculoResponse veiculoResponse = new VeiculoResponse(
        reserva.getVeiculo().getId(),
        reserva.getVeiculo().getMarca(),
        reserva.getVeiculo().getModelo(),
        reserva.getVeiculo().getAno(),
        reserva.getVeiculo().getCategoria(),
        reserva.getVeiculo().getTarifaDia(),
        reserva.getVeiculo().getStatusVeiculo());

    
    ReservaResponse response = new ReservaResponse(
        reserva.getId(),
        usuarioResponse,
        veiculoResponse,
        reserva.getDataInicio(),
        reserva.getDataFim(),
        reserva.getValorTotal());

    return ResponseEntity.ok(response);
  }

}
