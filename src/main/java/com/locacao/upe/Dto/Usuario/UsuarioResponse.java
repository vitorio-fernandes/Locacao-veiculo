package com.locacao.upe.Dto.Usuario;
import java.util.UUID;

import com.locacao.upe.Enum.Papel;
public record UsuarioResponse(
  UUID id,
  String nome,
  Papel papel
) {}
