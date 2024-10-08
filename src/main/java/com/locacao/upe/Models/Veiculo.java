package com.locacao.upe.Models;

import java.util.List;
import java.util.UUID;

import com.locacao.upe.Enum.StatusVeiculo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_VEICULO")
public class Veiculo {
  
  @Id
  @GeneratedValue
  UUID id;

  @NotBlank
  String marca;

  @NotBlank
  String modelo;

  @NotBlank
  String ano;

  @NotBlank
  String categoria;

  @NotNull
  double tarifaDia;
  
  @Enumerated(EnumType.STRING)
  StatusVeiculo statusVeiculo;

  @OneToMany(mappedBy = "Veiculo")
  List<Reserva> reservas;

}
