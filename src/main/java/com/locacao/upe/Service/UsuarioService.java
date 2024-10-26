package com.locacao.upe.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locacao.upe.Dto.Usuario.UsuarioRequest;
import com.locacao.upe.Models.Usuario;
import com.locacao.upe.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

  @Autowired
  UsuarioRepository usuarioRepository;

  @Transactional
  public Usuario cadastroUsuario(UsuarioRequest request) {
    Usuario usuario = new Usuario(request.nome(), request.email(), request.papel());
    return usuarioRepository.save(usuario);
  }

  @Transactional
  public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll();
  }

  @Transactional
  public Usuario atualizarUsuario(UUID id, UsuarioRequest request) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado !"));
    usuario.setNome(request.nome());
    usuario.setEmail(request.email());
    usuario.setPapel(request.papel());
    return usuarioRepository.save(usuario);
  }

  @Transactional
  public void deletarUsuario(UUID id) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado !"));
    usuarioRepository.delete(usuario);
  }

}
