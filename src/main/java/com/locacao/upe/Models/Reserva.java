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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TB_RESERVA")
public class Reserva {
  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "Veiculo_id")
  private Veiculo veiculo;
  
  @ManyToOne
  @JoinColumn(name = "Usuario_id")
  private Usuario usuario;

  @NotBlank
  private LocalDate dataInicio;

  @NotBlank
  private LocalDate dataFim;

  @NotNull
  private Double valorTotal;

  @Enumerated(EnumType.STRING)
  private StatusReserva statusReserva;


}
