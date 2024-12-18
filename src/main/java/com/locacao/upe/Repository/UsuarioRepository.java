package com.locacao.upe.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.locacao.upe.Models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository< Usuario,UUID >{

  UserDetails findByEmail(String email); 
  
} 