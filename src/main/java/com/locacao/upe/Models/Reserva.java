package com.locacao.upe.Models;

import java.time.LocalDate;
import java.util.UUID;

import com.locacao.upe.Enum.StatusReserva;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_RESERVA")
public class Reserva {
  @Id
  @GeneratedValue
  UUID id;

  @ManyToOne
  @JoinColumn(name = "Veiculo_id")
  Veiculo veiculo;
  
  @ManyToOne
  @JoinColumn(name = "Usuario_id")
  Usuario usuario;

  @NotBlank
  LocalDate dataInicio;

  @NotBlank
  LocalDate dataFim;

  @NotNull
  Double valorTotal;

  @Enumerated(EnumType.STRING)
  StatusReserva statusReserva;


}
