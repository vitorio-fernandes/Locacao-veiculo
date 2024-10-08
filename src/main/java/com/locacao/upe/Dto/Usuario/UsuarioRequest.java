package com.locacao.upe.Dto.Usuario;

import com.locacao.upe.Enum.Papel;

public record UsuarioRequest(

String nome,
String email,
Papel papel

) {}
