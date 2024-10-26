package com.locacao.upe.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locacao.upe.Dto.Reserva.ReservaRequest;
import com.locacao.upe.Enum.StatusReserva;
import com.locacao.upe.Enum.StatusVeiculo;
import com.locacao.upe.Models.Reserva;
import com.locacao.upe.Models.Usuario;
import com.locacao.upe.Models.Veiculo;
import com.locacao.upe.Repository.ReservaRepository;
import com.locacao.upe.Repository.UsuarioRepository;
import com.locacao.upe.Repository.VeiculoRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservaService {

  @Autowired
  ReservaRepository reservaRepository;

  @Autowired
  VeiculoRepository veiculoRepository;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Transactional
  public Reserva criarReserva(ReservaRequest request) {
    Veiculo veiculo = veiculoRepository.findById(request.veiculoId())
        .orElseThrow(() -> new IllegalStateException("Veículo não encontrado."));
    Usuario usuario = usuarioRepository.findById(request.usuarioId())
        .orElseThrow(() -> new IllegalStateException("Usuário não encontrado."));

    validarDatas(request.dataInicio(), request.dataFim()); // Valida as datas

    if (veiculo.getStatusVeiculo() == StatusVeiculo.DISPONIVEL) {
      double valorTotal = valorTotalReserva(request.dataInicio(), request.dataFim(), veiculo.getTarifaDia());

      veiculo.setStatusVeiculo(StatusVeiculo.ALUGADO);
      veiculoRepository.save(veiculo);

      Reserva reserva = new Reserva(
          veiculo,
          usuario,
          request.dataInicio(),
          request.dataFim(),
          valorTotal,
          StatusReserva.ATIVA);

      return reservaRepository.save(reserva);
    } else {
      throw new IllegalStateException("Veículo não disponível para reserva.");
    }
  }

  @Transactional
  public List<Reserva> listarReservas() {
    return reservaRepository.findAll();
  }

  @Transactional
  public Reserva atualizarReserva(UUID id, ReservaRequest request) {
    Reserva reserva = reservaRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Reserva não encontrada."));

    Veiculo veiculo = veiculoRepository.findById(request.veiculoId())
        .orElseThrow(() -> new IllegalStateException("Veículo não encontrado."));
    Usuario usuario = usuarioRepository.findById(request.usuarioId())
        .orElseThrow(() -> new IllegalStateException("Usuário não encontrado."));

    validarDatas(request.dataInicio(), request.dataFim()); // Valida as datas

    if (veiculo.getStatusVeiculo() == StatusVeiculo.DISPONIVEL || veiculo.equals(reserva.getVeiculo())) {
      double valorTotal = valorTotalReserva(request.dataInicio(), request.dataFim(), veiculo.getTarifaDia());
      reserva.setDataInicio(request.dataInicio());
      reserva.setDataFim(request.dataFim());
      reserva.setVeiculo(veiculo);
      reserva.setUsuario(usuario);
      reserva.setValorTotal(valorTotal);

      return reservaRepository.save(reserva);
    } else {
      throw new IllegalStateException("Veículo não disponível para reserva.");
    }
  }

  @Transactional
  public void deletarReserva(UUID id) {
    Reserva reserva = reservaRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Reserva não encontrada."));
    reservaRepository.delete(reserva);
  }

  public double valorTotalReserva(LocalDate dataInicio, LocalDate dataFim, double valorTarifa) {
    long dias = ChronoUnit.DAYS.between(dataInicio, dataFim);
    if (dias <= 0) {
      throw new IllegalArgumentException("A data de fim deve ser posterior à data de início.");
    }

    double valorTotal = dias * valorTarifa;

    // Aplica desconto para mais de 7 dias de reserva
    if (dias > 7) {
      valorTotal *= 0.95;
    }

    return valorTotal;
  }

  private void validarDatas(LocalDate dataInicio, LocalDate dataFim) {
    if (dataInicio.isAfter(dataFim)) {
      throw new IllegalArgumentException("A data de início deve ser anterior à data de fim.");
    }
  }
}
