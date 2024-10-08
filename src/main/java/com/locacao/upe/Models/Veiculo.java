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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "TB_VEICULO")
public class Veiculo {
  
  @Id
  @GeneratedValue
  private UUID id;

  @NotBlank
  private String marca;

  @NotBlank
  private String modelo;

  @NotBlank
  private String ano;

  @NotBlank
  private String categoria;

  @NotNull
  private double tarifaDia;
  
  @Enumerated(EnumType.STRING)
  private StatusVeiculo statusVeiculo;

  @OneToMany(mappedBy = "Veiculo")
  private List<Reserva> reservas;

}
