package com.pesterenan.parkingapi.service;

import java.util.List;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.VeiculoDTO;
import com.pesterenan.parkingapi.exception.VeiculoNotFoundException;

public interface IVeiculoService {
	
	// Cadastrar Veiculo
	public MessageResponseDTO createVeiculo(VeiculoDTO veiculoDTO);

	// Listar todos os Veiculos
	public List<VeiculoDTO> findAll();

	// Encontrar Veiculo pela placa
	public VeiculoDTO findByPlaca(String placa) throws VeiculoNotFoundException;

	// Encontrar veiculo pelo ID
	public VeiculoDTO findById(Long id) throws VeiculoNotFoundException;
	
	// Apagar veiculo do banco
	public void delete(Long id) throws VeiculoNotFoundException;

	public MessageResponseDTO updateById(Long id, VeiculoDTO veiculoDTO) throws VeiculoNotFoundException;

}
