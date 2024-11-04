package com.locacao.upe.Models;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails{
  
  @Id
  @GeneratedValue
  private UUID id;

  @NotBlank
  private String nome;
  
  @Email
  @NotBlank
  @Column(unique = true)
  private String email;

  @NotBlank
  @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
  private String senha;

  @Enumerated(EnumType.STRING)
  private Papel papel;

  @OneToMany(mappedBy = "usuario")
  private List<Reserva> reservas;

  public Usuario(@NotBlank String nome, @Email @NotBlank String email, Papel papel, @NotBlank String senha) {
    this.nome = nome;
    this.email = email;
    this.papel = papel; 
    this.senha = senha;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      if(this.papel == Papel.ADMIN){
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLER_USER"));
      }else{
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
      }
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public String getUsername() {
    return email;
  }
  
}
