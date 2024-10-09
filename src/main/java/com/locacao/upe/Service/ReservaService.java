package com.locacao.upe.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locacao.upe.Dto.Reserva.ReservaRequest;
import com.locacao.upe.Enum.StatusVeiculo;
import com.locacao.upe.Models.Reserva;
import com.locacao.upe.Repository.ReservaRepository;

@Service
public class ReservaService {
  @Autowired
  ReservaRepository reservaRepository;

  public Reserva criarReserva(ReservaRequest request) {
    // Verifica se o veículo está disponível
    if (request.veiculo().getStatusVeiculo() == StatusVeiculo.DISPONIVEL) {
      double valorTotal = valorTotalReserva(request.dataInicio(), request.dataFim(), request.veiculo().getTarifaDia());
      Reserva reserva = new Reserva(
          request.veiculo(),
          request.usuario(),
          request.dataInicio(),
          request.dataFim(),
          valorTotal,
          request.statusReserva());

      return reservaRepository.save(reserva);
    } else {
      throw new IllegalStateException("Veículo não disponível para reserva.");
    }
  }

  public List<Reserva> listarReservas(){
    return reservaRepository.findAll();
  }

  public Reserva atualizarReserva(UUID id, ReservaRequest request){
    Reserva reserva = reservaRepository.findById(id).orElseThrow(()-> new IllegalStateException("Veículo não encontrado."));
    if(request.veiculo().getStatusVeiculo() == StatusVeiculo.DISPONIVEL){
      double valorTotal = valorTotalReserva(request.dataInicio(), request.dataFim(), request.veiculo().getTarifaDia());
      reserva.setDataInicio(request.dataInicio());
      reserva.setDataFim(request.dataFim());
      reserva.setVeiculo(request.veiculo());
      reserva.setStatusReserva(request.statusReserva());
      reserva.setUsuario(request.usuario());
      reserva.setValorTotal(valorTotal);
      return reservaRepository.save(reserva);
    }else{
      throw new IllegalStateException("Veiculo não dispónivel para reserva.");
    } 
  }

  public void deletarReserva(UUID id){
    Reserva reserva = reservaRepository.findById(id).orElseThrow(()-> new IllegalStateException("Veículo não encontrado."));
    reservaRepository.delete(reserva);
  }

  // Método que calcula o valor total e desconto na hora da reserva
  public double valorTotalReserva(LocalDate dataInicio, LocalDate dataFim, double valorTarifa) {
    double valorTotal;
    long dias = ChronoUnit.DAYS.between(dataInicio, dataFim);
    valorTotal = dias * valorTarifa;

    if (dias > 7) {
      valorTotal *= 0.95;
    }

    return valorTotal;

  }
}
