package com.locacao.upe.Models;

import java.util.List;
import java.util.UUID;

import com.locacao.upe.Enum.Papel;

import jakarta.persistence.Column;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "TB_USUARIO")
public class Usuario {
  
  @Id
  @GeneratedValue
  private UUID id;

  @NotBlank
  private String nome;
  
  @Email
  @NotBlank
  @Column(unique = true)
  private String email;

  @Enumerated(EnumType.STRING)
  private Papel papel;

  @OneToMany(mappedBy = "usuario")
  private List<Reserva> reservas;

  public Usuario(@NotBlank String nome, @Email @NotBlank String email, Papel papel) {
    this.nome = nome;
    this.email = email;
    this.papel = papel;
  }
  
}
