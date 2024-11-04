package com.locacao.upe.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.locacao.upe.Dto.Usuario.UsuarioRegisterRequest;
import com.locacao.upe.Dto.Usuario.UsuarioRequest;
import com.locacao.upe.Enum.Papel;
import com.locacao.upe.Models.Usuario;
import com.locacao.upe.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

  @Autowired
  UsuarioRepository usuarioRepository;

  @Transactional
  public Usuario cadastroUsuario(UsuarioRegisterRequest request) {
    String senhaEncriptada = new BCryptPasswordEncoder().encode(request.senha()); // Criptografar Senha No Banco de Dados; HASH
    Usuario usuario = new Usuario(request.nome(), request.email(), Papel.CLIENTE ,senhaEncriptada); // Inicia padrão como cliente
    return usuarioRepository.save(usuario);
  }

  @Transactional
  public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll();
  }


  @Transactional
  public Usuario atualizarUsuario(UUID id, UsuarioRequest request) { //Apenas ADMIN podem acessar essa rota 
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado !"));

    usuario.setNome(request.nome());
    usuario.setEmail(request.email());
    if (request.senha() != null && !request.senha().isEmpty()) {
      usuario.setSenha(request.senha());
    }
    
    if (request.papel() != null) {
      usuario.setPapel(request.papel());
    }

    return usuarioRepository.save(usuario);
  }

  // Rota para clientes atualizarem apenas o próprio perfil
  @Transactional
  public Usuario atualizarPerfil(UUID id, UsuarioRequest request, UUID usuarioIdLogado) throws AccessDeniedException {
      if (!usuarioIdLogado.equals(id)) {
          throw new AccessDeniedException("Você só pode atualizar seu próprio perfil.");
      }

      Usuario usuario = usuarioRepository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado!"));

      usuario.setNome(request.nome());
      usuario.setEmail(request.email());
      if (request.senha() != null && !request.senha().isEmpty()) {
          usuario.setSenha(request.senha());
      }

      return usuarioRepository.save(usuario);
}


  @Transactional
  public void deletarUsuario(UUID id) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado !"));
    usuarioRepository.delete(usuario);
  }

}
