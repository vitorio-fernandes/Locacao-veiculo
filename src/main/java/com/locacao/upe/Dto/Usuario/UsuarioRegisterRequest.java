package com.locacao.upe.Dto.Usuario;

public record UsuarioRegisterRequest(
  String nome,
  String email,
  String senha
) {}