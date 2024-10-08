package com.locacao.upe.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locacao.upe.Models.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,UUID>{

  
} 