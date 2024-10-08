package com.locacao.upe.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locacao.upe.Models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository< Reserva, UUID > {

  
} 