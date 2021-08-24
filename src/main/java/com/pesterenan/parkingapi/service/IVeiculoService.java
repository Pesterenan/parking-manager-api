package com.pesterenan.parkingapi.service;

import java.util.List;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.dto.request.VeiculoDTO;
import com.pesterenan.parkingapi.exception.ClienteNotFoundException;

public interface IVeiculoService {
	
	// Cadastrar Veiculo
	public MessageResponseDTO createVeiculo(VeiculoDTO veiculoDTO);

	// Listar todos os Veiculos
	public List<VeiculoDTO> findAll();

	// Encontrar Veiculo pela placa
	public VeiculoDTO findByLicensePlate(Long id) throws ClienteNotFoundException;

	// Apagar veiculo do banco
	public void delete(Long id) throws ClienteNotFoundException;

	public MessageResponseDTO updateById(Long id, VeiculoDTO veiculoDTO) throws ClienteNotFoundException;
}
