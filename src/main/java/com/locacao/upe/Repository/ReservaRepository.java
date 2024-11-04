package com.locacao.upe.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locacao.upe.Enum.StatusReserva;
import com.locacao.upe.Models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository< Reserva, UUID > {
  
  List<Reserva> findByVeiculoIdAndStatusReserva(UUID veiculoId, StatusReserva statusReserva);
  
} 