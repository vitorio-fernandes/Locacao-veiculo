package com.locacao.upe.Controller;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locacao.upe.Dto.Reserva.ReservaRequest;
import com.locacao.upe.Dto.Reserva.ReservaResponse;
import com.locacao.upe.Dto.Usuario.UsuarioRequest;
import com.locacao.upe.Dto.Usuario.UsuarioResponse;
import com.locacao.upe.Dto.Veiculo.VeiculoResponse;
import com.locacao.upe.Models.Reserva;
import com.locacao.upe.Models.Usuario;
import com.locacao.upe.Models.Veiculo;
import com.locacao.upe.Service.ReservaService;
import com.locacao.upe.Service.UsuarioService;
import com.locacao.upe.Service.VeiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

        @Autowired
        VeiculoService veiculoService;
        @Autowired
        ReservaService reservaService;
        @Autowired
        UsuarioService usuarioService;

        @PutMapping("/{idUsuario}") //atualizar seu perfil       ??????
        public ResponseEntity<UsuarioResponse> atualizarPerfil(
                        @PathVariable UUID idUsuario,
                        @RequestBody @Valid UsuarioRequest request,
                        @RequestHeader UUID idLogado 
        ) throws AccessDeniedException {
                Usuario usuario = usuarioService.atualizarPerfil(idUsuario, request, idLogado);
                UsuarioResponse response = new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getPapel());
                return ResponseEntity.ok(response);
        }

        @PostMapping("/reserva") //realizar reserva
        public ResponseEntity<ReservaResponse> criarReserva(@Valid @RequestBody ReservaRequest request) {
                Reserva reserva = reservaService.criarReserva(request);

                UsuarioResponse usuarioResponse = new UsuarioResponse(
                                reserva.getUsuario().getId(),
                                reserva.getUsuario().getNome(),
                                reserva.getUsuario().getPapel());

                VeiculoResponse veiculoResponse = new VeiculoResponse(
                                reserva.getVeiculo().getId(),
                                reserva.getVeiculo().getMarca(),
                                reserva.getVeiculo().getModelo(),
                                reserva.getVeiculo().getAno(),
                                reserva.getVeiculo().getCategoria(),
                                reserva.getVeiculo().getTarifaDia(),
                                reserva.getVeiculo().getStatusVeiculo());

                ReservaResponse response = new ReservaResponse(
                                reserva.getId(),
                                usuarioResponse,
                                veiculoResponse,
                                reserva.getDataInicio(),
                                reserva.getDataFim(),
                                reserva.getValorTotal());

                return ResponseEntity.ok(response);
        }

        @GetMapping("/reserva") // Listas veiculos
        public ResponseEntity<List<VeiculoResponse>> listarVeiculos() {
                List<VeiculoResponse> response = new ArrayList<>();

                for (Veiculo veiculo : veiculoService.listarVeiculosDisponiveis()) {
                        VeiculoResponse veiculoResponse = new VeiculoResponse(
                                        veiculo.getId(),
                                        veiculo.getMarca(),
                                        veiculo.getModelo(),
                                        veiculo.getAno(),
                                        veiculo.getCategoria(),
                                        veiculo.getTarifaDia(),
                                        veiculo.getStatusVeiculo());
                        response.add(veiculoResponse);
                }
                return ResponseEntity.ok(response);
        }

        @PutMapping("/reserva/{id}") // Atualizar sua reserva
        public ResponseEntity<ReservaResponse> atualizarReserva(@PathVariable UUID id,
                        @Valid @RequestBody ReservaRequest request) {
                Reserva reserva = reservaService.atualizarReserva(id, request);

                UsuarioResponse usuarioResponse = new UsuarioResponse(
                                reserva.getUsuario().getId(),
                                reserva.getUsuario().getNome(),
                                reserva.getUsuario().getPapel());

                VeiculoResponse veiculoResponse = new VeiculoResponse(
                                reserva.getVeiculo().getId(),
                                reserva.getVeiculo().getMarca(),
                                reserva.getVeiculo().getModelo(),
                                reserva.getVeiculo().getAno(),
                                reserva.getVeiculo().getCategoria(),
                                reserva.getVeiculo().getTarifaDia(),
                                reserva.getVeiculo().getStatusVeiculo());
                ReservaResponse response = new ReservaResponse(
                                reserva.getId(),
                                usuarioResponse,
                                veiculoResponse,
                                reserva.getDataInicio(),
                                reserva.getDataFim(),
                                reserva.getValorTotal());

                return ResponseEntity.ok(response);
        }

}
