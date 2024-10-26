package com.locacao.upe.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locacao.upe.Dto.Veiculo.VeiculoRequest;
import com.locacao.upe.Models.Veiculo;
import com.locacao.upe.Repository.VeiculoRepository;

import jakarta.transaction.Transactional;

@Service
public class VeiculoService {
  @Autowired
  VeiculoRepository veiculoRepository;

  @Transactional
  public Veiculo adicionarVeiculo(VeiculoRequest request) {
    Veiculo veiculo = new Veiculo(
        request.marca(),
        request.modelo(),
        request.ano(),
        request.categoria(),
        request.tarifaDia(),
        request.statusVeiculo());
    return veiculoRepository.save(veiculo);
  }

  @Transactional
  public List<Veiculo> listarVeiculos() {
    return veiculoRepository.findAll();
  }

  @Transactional
  public Veiculo atualizarVeiculo(UUID id, VeiculoRequest request) {
    Veiculo veiculo = veiculoRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Veiculo não encontrado !"));
    veiculo.setAno(request.ano());
    veiculo.setCategoria(request.categoria());
    veiculo.setMarca(request.marca());
    veiculo.setModelo(request.modelo());
    veiculo.setStatusVeiculo(request.statusVeiculo());
    veiculo.setTarifaDia(request.tarifaDia());
    return veiculoRepository.save(veiculo);
  }

  @Transactional
  public void deletarVeiculo(UUID id) {
    Veiculo veiculo = veiculoRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Veiculo não encontrado !"));
    veiculoRepository.delete(veiculo);
  }
}
