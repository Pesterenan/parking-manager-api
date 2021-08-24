package com.pesterenan.parkingapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.VeiculoDTO;
import com.pesterenan.parkingapi.service.IVeiculoService;

@RestController
@RequestMapping("/api/v1/veiculos")
public class VeiculoController {

	@Autowired
	private IVeiculoService veiculoService;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO cadastrarVeiculo(@RequestBody @Valid VeiculoDTO veiculoDTO) {
		return veiculoService.createVeiculo(veiculoDTO);
	}

	// Mapeamento para buscar todos os veículos
	@GetMapping
	public List<VeiculoDTO> getAllVeiculos() {
		return veiculoService.findAll();
	}

}
