package com.locacao.upe.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locacao.upe.Dto.Usuario.UsuarioLoginRequest;
import com.locacao.upe.Dto.Usuario.UsuarioRegisterRequest;
import com.locacao.upe.Dto.Usuario.UsuarioResponse;
import com.locacao.upe.Models.Usuario;
import com.locacao.upe.Repository.UsuarioRepository;
import com.locacao.upe.Security.TokenService;
import com.locacao.upe.Service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  UsuarioService usuarioService;

  @Autowired
  TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid UsuarioLoginRequest login) {
    try {
      var usuarioSenha = new UsernamePasswordAuthenticationToken(login.email(), login.senha());
      var auth = authenticationManager.authenticate(usuarioSenha);
      var token = tokenService.gerarTokenJWT((Usuario) auth.getPrincipal());
      
      return ResponseEntity.ok(token);

    } catch (BadCredentialsException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> registro(@RequestBody @Valid UsuarioRegisterRequest usuarioLoginRequest) {
    try {
      if (usuarioRepository.findByEmail(usuarioLoginRequest.email()) != null) {
        return ResponseEntity.badRequest().body("Usuário já cadastrado no sistema !");
      }
      Usuario novoUsuario = usuarioService.cadastroUsuario(usuarioLoginRequest);
      UsuarioResponse response = new UsuarioResponse(novoUsuario.getId(), novoUsuario.getNome(),
          novoUsuario.getPapel());
      return ResponseEntity.status(HttpStatus.CREATED).body(response);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivél criar o usuário !");
    }
  }
}
