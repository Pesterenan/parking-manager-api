package com.pesterenan.parkingapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.VeiculoDTO;
import com.pesterenan.parkingapi.exception.VeiculoNotFoundException;
import com.pesterenan.parkingapi.service.VeiculoService;

@RestController
@RequestMapping("/api/v1/veiculos")
public class VeiculoController {

	private VeiculoService veiculoService;

	@Autowired
	public VeiculoController(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO cadastrarVeiculo(@RequestBody @Valid VeiculoDTO veiculoDTO) {
		return veiculoService.createVeiculo(veiculoDTO);
	}

	// Mapeamento para buscar todos os veículos
	@GetMapping
	public List<VeiculoDTO> getAllVeiculos() throws VeiculoNotFoundException {
		return veiculoService.findAll();
	}

	// Mapeamento para atualizar veiculo
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid VeiculoDTO veiculoDTO) throws VeiculoNotFoundException {
		return veiculoService.updateById(id, veiculoDTO);
		
	}
	
	// Buscar Veiculo por placa, retorna "Veiculo não encontrado" caso não exista.
	@GetMapping("/buscarPlaca/{placa}")
	@ResponseStatus(HttpStatus.OK)
	public VeiculoDTO getVeiculoByPlaca(@PathVariable String placa) throws VeiculoNotFoundException {
		return veiculoService.findByPlaca(placa);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws VeiculoNotFoundException {
		veiculoService.delete(id);
	}
}
