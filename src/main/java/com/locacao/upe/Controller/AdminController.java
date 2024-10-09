package com.locacao.upe.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locacao.upe.Service.ReservaService;
import com.locacao.upe.Service.UsuarioService;
import com.locacao.upe.Service.VeiculoService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
  @Autowired
  UsuarioService usuarioService;
  @Autowired
  VeiculoService veiculoService;
  @Autowired
  ReservaService reservaService;

}
