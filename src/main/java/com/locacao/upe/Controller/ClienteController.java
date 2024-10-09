package com.locacao.upe.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.locacao.upe.Service.ReservaService;
import com.locacao.upe.Service.VeiculoService;

public class ClienteController {
  
  @Autowired
  VeiculoService veiculoService;
  @Autowired
  ReservaService reservaService;
}
