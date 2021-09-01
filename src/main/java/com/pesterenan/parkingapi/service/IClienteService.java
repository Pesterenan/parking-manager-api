package com.pesterenan.parkingapi.service;

import java.util.List;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.exception.ClienteAlreadyRegisteredException;
import com.pesterenan.parkingapi.exception.ClienteNotFoundException;

public interface IClienteService {
	
	// Criar Cliente
	public ClienteDTO createCliente(ClienteDTO clienteDTO) throws ClienteAlreadyRegisteredException;

	// Listar todos os Clientes
	public List<ClienteDTO> findAll();

	// Encontrar ID do cliente
	public ClienteDTO findById(Long id) throws ClienteNotFoundException;
	
	// Encontrar cliente por CPF
	public ClienteDTO findByCpf(String cpf) throws ClienteNotFoundException;

	// Apagar cliente do banco
	public void delete(Long id) throws ClienteNotFoundException;

	public MessageResponseDTO updateById(Long id, ClienteDTO clienteDTO) throws ClienteNotFoundException;
}
