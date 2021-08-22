package com.pesterenan.parkingapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pesterenan.parkingapi.dto.MessageResponseDTO;
import com.pesterenan.parkingapi.dto.request.ClienteDTO;
import com.pesterenan.parkingapi.entity.Cliente;
import com.pesterenan.parkingapi.exceptionqq.ClienteNotFoundException;
import com.pesterenan.parkingapi.mapper.ClienteMapper;
import com.pesterenan.parkingapi.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	// Criar Cliente
	public MessageResponseDTO createCliente(ClienteDTO clienteDTO) {
		Cliente clienteToSave = clienteMapper.toModel(clienteDTO);
		Cliente savedCliente = clienteRepository.save(clienteToSave);
		return MessageResponseDTO.builder().message("Cliente criado com ID: " + savedCliente.getId()).build();
	}

	// Listar todos os Clientes
	public List<ClienteDTO> findAll() {
		List<Cliente> allClientes = clienteRepository.findAll();
		return allClientes.stream().map(clienteMapper::toDTO).collect(Collectors.toList());
	}

	// Encontrar ID do cliente
	public ClienteDTO findById(Long id) throws ClienteNotFoundException {
		Cliente cliente = verifyIfExists(id);
		return clienteMapper.toDTO(cliente);
	}

	// Apagar cliente do banco
	public void delete(Long id) throws ClienteNotFoundException {
		verifyIfExists(id);
		clienteRepository.deleteById(id);
	}

	// Verificar se a ID informada existe
	private Cliente verifyIfExists(Long id) throws ClienteNotFoundException {
		return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
	}
}
