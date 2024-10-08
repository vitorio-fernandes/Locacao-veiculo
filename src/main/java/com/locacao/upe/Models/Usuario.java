package com.locacao.upe.Models;

import java.util.List;
import java.util.UUID;

import com.locacao.upe.Enum.Papel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USUARIO")
public class Usuario {
  
  @Id
  @GeneratedValue
  UUID id;

  @NotBlank
  String nome;
  
  @Email
  @NotBlank
  String email;

  @Enumerated(EnumType.STRING)
  Papel papel;

  @OneToMany(mappedBy = "Usuario")
  List<Reserva> reservas;
  
}
